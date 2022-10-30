package hyboard.Board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hyboard.Board.domain.Board;
import hyboard.Board.entity.BoardEntity;
import hyboard.Board.repository.BoardRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "boardService")
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public void setSongRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @PostConstruct
    public void prepare() {
        BoardEntity songEntity = new BoardEntity();
        songEntity.setTitle("테스트 게시글");
        songEntity.setContent("내용입니다");
        songEntity.setAuthor("작성자");
        songEntity.setCreatedAt(LocalDateTime.now());
        songEntity.setViewCount(0);
        boardRepository.save(songEntity);
    }


    @Override
    public Board write(Board board) {
        /*
        SongEntity songEntity = new SongEntity(0L,
                song.getTitle(), song.getSinger(), song.getComposer(),
                song.getYear());
         */

        BoardEntity songEntity = new BoardEntity(null, board.getTitle(), board.getContent(), board.getAuthor(), 0, LocalDateTime.now());
        boardRepository.save(songEntity);

        return board;
    }

    @Override
    public List<Board> getList() {
        List<BoardEntity> list = boardRepository.findAllByOrderByCreatedAtDesc();
        return makeWriteList(list);
    }

    private List<Board> makeWriteList(List<BoardEntity> list) {
        List<Board> result = new ArrayList<>();
        System.out.println(list);
        for (BoardEntity item : list) {
            System.out.println(item);
            Board board = new Board(item.getIdx(), item.getTitle(), item.getContent(), item.getAuthor(), item.getViewCount(), item.getCreatedAt());
            result.add(board);
        }

        return result;
    }

    @Override
    public Board read(Long idx) {
        Optional<BoardEntity> optional = boardRepository.findById(idx);

        if (optional.isPresent()) {
            BoardEntity entity = optional.get();
            Board board = new Board(entity.getIdx(), entity.getTitle(),
                    entity.getContent(), entity.getAuthor(), entity.getViewCount(), entity.getCreatedAt());

            return board;

        } else {
            throw new IllegalArgumentException("잘못된 IDX 입니다.");
        }
    }

    @Override
    public void update(Board board) {
        BoardEntity entity = new BoardEntity(board.getIdx(), board.getTitle(),
                board.getContent(), board.getAuthor(), board.getReadCount(), board.getCreatedAt());

        boardRepository.save(entity);
    }

    @Override
    public void delete(Long idx) {
        boardRepository.deleteById(idx);
    }
}