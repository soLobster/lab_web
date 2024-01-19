package com.itwill.springboot4.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl{
        
    
    public PostQuerydslImpl() {
        super(Post.class);
        // -> 상위 클래스 QuerydslRepositorySupport는 기본 생성자를 갖고 있지 않기 때문에
        // 아규먼트를 갖는 생성자를 명시적으로 호출해야만 함.
        // QuerydslRepositorySupport 생성자의 아규먼트는 도메인 클래스 (엔터티 클래스).
    }

    @Override
    public Post searchById(Long id) {
        // id(PK)로 포스트 1개를 검색하는 기능.
        // select p.* from posts
        log.info("searchById(id = {})", id);
        
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post); // select ... from post p
        query.where(post.id.eq(id)); // where p.id = ?
        Post entity = query.fetchOne();
        
        return entity;
    }

    @Override
    public List<Post> searchByTitleOrContent(String keyword) {
        log.info("searchByTitleOrContent(KEYWORD = {})", keyword);
        
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post); // select p from Post p
        query.where(post.title.containsIgnoreCase(keyword)
             .or(post.content.containsIgnoreCase(keyword)));
        // where p.title like upper(?) or p.content like upper(?)
        query.orderBy(post.id.desc());
        
        return query.fetch();
    }

    @Override
    public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
        log.info("searchByModifiedTime() from = {}, to = {}", from, to);
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);
        query.where(post.modifiedTime.between(from, to));
        query.orderBy(post.modifiedTime.desc());
        
        return query.fetch();
    }

    @Override
    public List<Post> searchByKeywordAndAuthor(String keyword, String author) {
        log.info("searchByKeywordAndAuthor() keyword = {}, author = {}", keyword, author);
        
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword))); // (x or y) and ()
        builder.and(post.author.eq(author));
        query.where(builder);
        
        return query.fetch();
    }

    @Override
    public List<Post> searchBykeywords(String[] keywords) {
        log.info("searchBykeywords keywords = {}", Arrays.asList(keywords));
        
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);
        BooleanBuilder builder = new BooleanBuilder();
        for(String keyword : keywords) {
            builder.or(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword)));
            //query.where(post.title.containsIgnoreCase(keyword));
        }
        query.where(builder);
        query.orderBy(post.id.desc());
        
        return query.fetch();
    }

    @Override
    public Page<Post> searchByKeywords(String[] keywords, Pageable pageable) {
        log.info("searchTitleByKeywords = {}", Arrays.asList(keywords), pageable);
        
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post); // select from
        
//        int page = 0;
//        for(page = 0; page < 6; page ++) {
//            pageable = PageRequest.of(page, 5, Sort.by("id").descending());     
//        }
        BooleanBuilder builder = new BooleanBuilder();
        for(String keyword : keywords) {
            builder.or(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword)));
            //query.where(post.title.containsIgnoreCase(keyword));
        }
        query.where(builder); // where
        
        // 페이징 & 정렬 적용 
        getQuerydsl().applyPagination(pageable, query);
        
        // 한 페이지에 표시될 데이터
        List<Post> list = query.fetch();
        // 전체 원소 개수
        long total = query.fetchCount(); 
        // Page<T> 타입 객체를 생성
        Page<Post> page = new PageImpl<Post>(list, pageable, total);
        return page;
    }
    
}
