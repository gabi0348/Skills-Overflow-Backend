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

    public Object[] searchForPosts(String queryParam, Integer pageNo){
        int noOfPages = postRepository.findAll().size() / 10 + 1;
        if (pageNo > noOfPages) {
            return null;
        }

        List<PostDTO> searchedPosts = getQueryStream(queryParam)
                .sorted(Comparator.comparingInt(post -> {
                    String[] bodyArray = post.getBody().split("\\s+");
                    String[] titleArray = post.getTitle().split("\\s+");
                    String[] both = ArrayUtils.addAll(bodyArray, titleArray);

                    int count = 0;
                    for (String s: both) {
                        if (s.toLowerCase().equals(queryParam.toLowerCase()))
                            count ++;
                    }
                    System.out.println(count);
                    return count;
                }))
                .skip(pageNo * 10)
                .limit(10)
                .map(PostConverter::convertToPostDTO)
                .collect(Collectors.toList());

        Collections.reverse(searchedPosts);

        Object[] object = new Object[2];
        object[0] = (int) getQueryStream(queryParam).count();
        object[1] = searchedPosts;
        return object;
        //faci aici convertirea direct
    }

    private Stream<Post> getQueryStream(String queryParam) {
        return postRepository.findAll()
                .stream()
                .filter(post->post.getTitle().toLowerCase().contains(queryParam.toLowerCase()));
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
