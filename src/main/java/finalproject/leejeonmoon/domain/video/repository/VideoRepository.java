package finalproject.leejeonmoon.domain.video.repository;

import finalproject.leejeonmoon.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
