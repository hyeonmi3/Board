package hyboard.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hyboard.Board.domain.Board;
import hyboard.Board.entity.BoardEntity;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByOrderByCreatedAtDesc();

}