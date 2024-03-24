package shop.mtcoding.blog.reply;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/{replyId}/delete")
    public String delete (@RequestParam(name = "boardId") Integer boardId, @PathVariable Integer replyId){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(sessionUser.getId(), replyId);

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/reply/save")
    public String save (ReplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글쓰기(reqDTO, sessionUser);
        return "redirect:/board/" + reqDTO.getBoardId();
    }
}
