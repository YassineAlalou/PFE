package com.sqli.ecommerce.reviewv2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqli.ecommerce.reviewv2.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
