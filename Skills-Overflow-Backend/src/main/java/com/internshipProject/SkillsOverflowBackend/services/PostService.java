package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {

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
        //updatePost.setComments(post.getComments());
        //updatePost.setCreateDate(post.getCreateDate());
        //updatePost.setUser(post.getUser());

        updatePost.setBody(post.getBody());
        updatePost.setTitle(post.getTitle());
        updatePost.setTopic(post.getTopic());
        postRepository.save(updatePost);
        return updatePost;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }


    //https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
    //asta e baza!!!
    public List<Post> getAllFilteredPosts(Integer pageNo, String criteria, String topic) {

        int noOfPages = postRepository.findAll().size() / 10 + 1;
        if (pageNo > noOfPages) {
            return new ArrayList<>();
        }

        Stream<Post> allPosts = postRepository.findAll()
                .stream();
        if (! (topic.equals("undefined") || (topic.equals("all"))) ) {

            //filtare pe postari
            Stream<Post> postTopicList = allPosts
                    .filter(post -> post.getTopic().equals(topic));

            //aici automat topicurile nu sunt undefined
            if (!criteria.equals("undefined")) {
                //return getPostsOnTopicAndCriteria(criteria, posts, pageNo, topic);
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

    private List<Post> getPostsOnCriteria(String criteria, Stream<Post> posts, Integer pageNo) {
        if (criteria.equals("date")) {
            return posts.sorted(Comparator.comparing(Post::getCreateDate).reversed())
                    .skip(pageNo * 10)
                    .limit(10)
                    .collect(Collectors.toList());
        }

        if (criteria.equals("comms")){
            return posts.sorted(Comparator.comparing(Post::getNumberOfComments).reversed())
                    .skip(pageNo * 10)
                    .limit(10)
                    .collect(Collectors.toList());
        }
        return null;
    }

    //de adaugat in controller
    private List<Post> getPostsJustByDate(Integer pageNo, Stream<Post> posts) {
        return posts
                .sorted(Comparator.comparing(Post::getCreateDate).reversed())
                .skip(pageNo*10)
                .limit(10)
                .collect(Collectors.toList());
    }


}
