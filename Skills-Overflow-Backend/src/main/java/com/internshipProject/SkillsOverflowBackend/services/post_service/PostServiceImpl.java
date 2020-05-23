package com.internshipProject.SkillsOverflowBackend.services.post_service;

import com.internshipProject.SkillsOverflowBackend.convertors.PostConverter;
import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.models.*;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    public Post save(Post post) {
        postRepository.save(post);
        return post;
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post updateAndSavePost(Post updatePost, Post post) {
        updatePost.setBody(post.getBody());
        updatePost.setTitle(post.getTitle());
        updatePost.setTopics(post.getTopics());
        postRepository.save(updatePost);
        return updatePost;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }


    //https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
    //asta e baza!!!
   public List<PostDTO> getAllFilteredPosts(Integer pageNo, String criteria, TopicFront topic) {

        int noOfPages = postRepository.findAll().size() / 10 + 1;
        if (pageNo > noOfPages) {
            return new ArrayList<>();
        }

        Stream<Post> allPosts = postRepository.findAll()
                .stream()
                .filter(Post::getIsApproved);
        //null???
       //aici DOAR DACA am filtrare - array primit din front-end
       return getFilteredAndSortedPostDTOS(pageNo, criteria, topic, allPosts);
   }

    public List<PostDTO> getFilteredAndSortedPostDTOS(Integer pageNo, String criteria, TopicFront topic, Stream<Post> allPosts) {

        if (topic.getTopics().length>0) {

            //filtare pe postari
            Stream<Post> postTopicList = getPostWithTopicStream(topic, allPosts);

            //aici automat topicurile nu sunt undefined
            if (!criteria.equals("undefined")) {
                return getPostsOnCriteria(criteria, postTopicList, pageNo);
            }
            //aici ajunge in caz ca avem topicuri(!), dar nu au criterii; le sortez dupa data
            else {
                return getPostsJustByDate(pageNo, postTopicList);
            }
        }

        //else topicurile sunt undefined
        else {
            if(!criteria.equals("undefined")) {
                return getPostsOnCriteria(criteria, allPosts, pageNo);
            }
            //toate postarile buluc, doar in functie de data; topicul si criteria sunt undefined
            else {
                return getPostsJustByDate(pageNo, allPosts);
            }
        }
    }

    public Stream<Post> getPostWithTopicStream(TopicFront topic, Stream<Post> allPosts) {
        return allPosts
                        .filter(post -> {
                            int found = 0;
                            int totalNumberOfTopics = topic.getTopics().length;
                            for(Topic topic1 :post.getTopics()) {
                                String topicName = topic1.getTopic();
                                for (String frontTopic: topic.getTopics()) {
                                    if (topicName.equals(frontTopic)) {
                                        found++;
                                        if (found == totalNumberOfTopics) return true;
                                    }
                                }
                            }
                            return false;
                        });
    }

    private List<PostDTO> getPostsOnCriteria(String criteria, Stream<Post> posts, Integer pageNo) {
        if (criteria.equals("date")) {
            return posts.sorted(Comparator.comparing(Post::getCreateDate).reversed())
                    .skip(pageNo * 10)
                    .limit(10)
                    .map(PostConverter::convertToPostDTO)
                    .collect(Collectors.toList());
        }

        if (criteria.equals("comms")){
            return posts.sorted(Comparator.comparing(Post::getNumberOfComments).reversed())
                    .skip(pageNo * 10)
                    .limit(10)
                    .map(PostConverter::convertToPostDTO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    //metoda utilitara
    private List<PostDTO> getPostsJustByDate(Integer pageNo, Stream<Post> posts) {
        return posts
                .sorted(Comparator.comparing(Post::getCreateDate).reversed())
                .skip(pageNo*10)
                .limit(10)
                .map(PostConverter::convertToPostDTO)
                .collect(Collectors.toList());
    }

    public Object[] searchForPosts(String queryParam, Integer pageNo, String criteria,
                                   TopicFront topic){
        int noOfPages = postRepository.findAll().size() / 10 + 1;
        if (pageNo > noOfPages) {
            return null;
        }

        Object[] object = new Object[3]; //obiectul pe care il voi returna

        String paramLowerCase= queryParam.toLowerCase();

        List<Post> searchedPosts = getQueryStream(paramLowerCase)
                .sorted(Comparator.comparingInt(post -> {
                    String[] all = getSearchedStrings(post, paramLowerCase);

                    int count = 0;
                    for (String s: all) {
                        if (s.toLowerCase().equals(paramLowerCase))
                            count ++;
                    }
                    return count;
                }))
//                .skip(pageNo * 10)
//                .limit(10)
                .collect(Collectors.toList());

        if (searchedPosts.size() == 0) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            postRepository.findAll()
                    .stream()
                    .map(this::getSearchedStringsByTopic)// i get ALL strings
                    .flatMap(Stream::of)// then i convert them to strings
                    .filter(s->s.length()>2)
                    .filter(string-> Owner.equalStrings(string, paramLowerCase))
                    .forEach(s->{
                        hashMap.merge(s, 1, Integer::sum);
                        System.out.println(hashMap);
                    }); // ce tare e auto-complete :)))))

            List<Map.Entry<String, Integer>> entries = hashMap.entrySet()
                     .stream()
                     .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                     .collect(Collectors.toList());
            object[2] = entries.isEmpty() ? null : entries.get(0);
        }

        List<PostDTO> postDTOS = getFilteredAndSortedPostDTOS(pageNo, criteria, topic, searchedPosts.stream());
        //in ordinea descrescatoare a comentariilor

        //aici trebuie schimbat ca intoarce numarul gresit
        object[0] = (int) getQueryStream(queryParam).count();
        object[1] = postDTOS;
        return object;
    }

    //AICI le fac pe toate lower case!!! metoda utilitare
    private String[] getSearchedStrings(Post post, String queryParam) {
        String[] bodyArray = post.getBody().split("[^a-zA-Z']+");
        String[] titleArray = post.getTitle().split("[^a-zA-Z']+"); // nu \s+ !!!
        String[] topicList = post.getTopics().
                stream()
                .map(topic-> topic.getTopic().replaceAll("\\s+","")) //scot whitespace de la topics
                .toArray(String[]::new);
        String[] both = ArrayUtils.addAll(bodyArray, titleArray);
        String[] all = ArrayUtils.addAll(topicList, both);

        //sa nu mai fac split cu regex??
        return Arrays.stream(all)
                .map(s -> s.split("(?=" + queryParam + ")"))
                .flatMap(Stream::of)
                .distinct()
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }

    //aici imi filtra ca doar pe cele in titlu cu java sa apara
    private Stream<Post> getQueryStream(String queryParam) {
        return postRepository.findAll()
                .stream()
                .filter(Post::getIsApproved) //if the post is approved !!
                .filter(post->  Arrays
                        .asList(getSearchedStrings(post, queryParam)) //aici caut toate stringurile si fac si split, caut in array parametru
                        .contains(queryParam.toLowerCase()));
    }

    private String[] getSearchedStringsByTopic(Post post){
       return post.getTopics()
               .stream()
               .map(topic-> topic.getTopic().replaceAll("\\s+",""))
               .map(s-> {
                   System.out.println("this is the strong-->" + s);
                   return s.toLowerCase();
               })
               .distinct()
               .toArray(String[]::new);
    }

    public Integer getNumberOfPosts(){
        return postRepository.findAll().size();
    }

    public Object[] getPostWithSortedComments(Long postId, Long pageNo, User user) {

        //hardcodez page number sa fie 0 oricum!!!!
        pageNo = 0L;

        Object[] array = new Object[3];

        Optional<Post> optionalPost = findById(postId);
        if (optionalPost.isPresent()) {

            List<Comment> commentList = new ArrayList<>();
            Post post = optionalPost.get();
            array[0] = PostConverter.convertToPostDTO(post);

            //daca sunt mai multe pagini decat am
            int noOfPages = post.getComments().size() / 10 + 1;
            if (pageNo > noOfPages) {
                return null;
            }

            int comLimit = 10;
            //daca imi trimite pagina nr.0 (prima); din toate comentariile, il caut pe cel most relevant
            List<Comment> comments = new ArrayList<>();
            if (pageNo == 0) {
                comLimit = 9;
                comments = post.getComments().
                        stream().
                        filter(Comment::getIsMostRelevantComment).
                        collect(Collectors.toList());
            }

            //compar in functie de vote count
            List<Comment> sortedComments = post.getComments()
                    .stream()
                    .sorted(Comparator.comparing(Comment::getVoteCount).reversed())
                    .skip(pageNo * 10)
                    .limit(comLimit)
                    .collect(Collectors.toList());

            //aici voi converti si comentariile in dto
            comments.addAll(sortedComments);
            array[1] = comments;
            array[2] = Owner.isPrincipalOwnerOfPost(user, post);
            return array;
        }

        return null;
    }

}