package com.example.demo.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query("delete from PostEntity p where p.publicId = :publicId")
    void deleteByPublicId(String publicId);

}
