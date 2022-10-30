package hyboard.Board.service;

import hyboard.Board.domain.Board;

import java.util.List;

public interface BoardService {
    /**
     * 새 노래를 등록한다.
     *
     * @param board 노래 정보
     * @return 등록된 노래 정보
     */
    public Board write(Board board);

    /**
     * 등록된 노래 목록을 가져온다.
     *
     * @return 등록된 노래 목록
     */
    public List<Board> getList();

    /**
     * 주어진 idx을 가진 노래를 가져온다.
     *
     * @param idx 노래 idx
     * @return 노래
     */
    public Board read(Long idx);

    public void update(Board board);

    public void delete(Long idx);

}