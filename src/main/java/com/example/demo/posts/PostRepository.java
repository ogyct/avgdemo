package com.example.demo.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query("delete from PostEntity p where p.publicId = :publicId")
    void deleteByPublicId(String publicId);
}
