package hyboard.Board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import hyboard.Board.domain.Board;
import hyboard.Board.service.BoardService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public void setSongService(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/list";
    }


    @RequestMapping("/write")
    public String add() {
        return "song/write";
    }

    @PostMapping("/write-save.do")
    public String addSave(
            @RequestParam("title") String title
            , @RequestParam("content") String content
            , @RequestParam("author") String author) {

        // 데이터베이스에 게시글 저장
        Board board = new Board(null, title, content, author, 0, LocalDateTime.now());
        boardService.write(board);

        return "redirect:list";
    }

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "type", defaultValue = "제목") String type,
            @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView("song/list");

        mv.addObject("list", boardService.getList());

        return mv;
    }

    @RequestMapping("/view/{idx}")
    public ModelAndView view(
            @PathVariable("idx") Long idx) {
        ModelAndView mv = new ModelAndView("song/view");

        Board board = boardService.read(idx);
        board.setReadCount(board.getReadCount() + 1);
        mv.addObject("board", board);

        boardService.update(board);

        return mv;
    }

    @GetMapping("/update/{idx}")
    public ModelAndView update(
            @PathVariable("idx") Long idx) {
        ModelAndView mv = new ModelAndView("song/update");

        Board board = boardService.read(idx);
        mv.addObject("board", board);

        return mv;
    }

    @RequestMapping("/update-save.do")
    public String updateSave(
            @RequestParam("idx") Long idx
            , @RequestParam("title") String title
            , @RequestParam("content") String content
            , @RequestParam("author") String author) {

        Board board = boardService.read(idx);
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);

        boardService.update(board);

        return "redirect:view/" + idx;
    }

    @RequestMapping("/delete.do/{idx}")
    public String delete(@PathVariable("idx") Long idx) {
        boardService.delete(idx);

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}