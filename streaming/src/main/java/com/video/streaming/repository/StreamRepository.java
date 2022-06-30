
package com.video.streaming.repository;

import com.video.streaming.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Interface StreamRepository.
 */
@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the stream
     */
    Stream findByStreamId(long id);

}
