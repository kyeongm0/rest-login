package com.lcomputerstudy.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcomputerstudy.example.domain.Answer;
import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Comment;
import com.lcomputerstudy.example.domain.Item;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.Question;
import com.lcomputerstudy.example.domain.Search;
import com.lcomputerstudy.example.domain.Survey;
import com.lcomputerstudy.example.domain.Upload;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.request.AnswerJoinRequest;
import com.lcomputerstudy.example.request.BoardJoinRequest;
import com.lcomputerstudy.example.request.CommentJoinRequest;
import com.lcomputerstudy.example.request.JoinRequest;
import com.lcomputerstudy.example.request.SurveyJoinRequest;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.CommentService;
import com.lcomputerstudy.example.service.SurveyService;
import com.lcomputerstudy.example.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SurveyService surveyService;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user/{pageNum}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  userList(@PathVariable(value = "pageNum") int pageNum) {
		int page = pageNum;
		int count = userService.userCount();
		
		User user = new User();
		Pagination pagination = new Pagination();
		
		pagination.setPage(page);
		pagination.setCount(count);
		pagination.page();
		
		user.setPagination(pagination);
		
		List<User> userList = userService.selectUserList(user);
		
		userList.get(0).setPagination(pagination);
		
		return ResponseEntity.ok(userList);
	}
	
	@GetMapping("/userDetail")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  userDetail(@RequestParam String username) {
		
		logger.info("///"+username);
		
		User user = userService.selectUserDetail(username);
		user.setAuthorities(userService.readAuthorities_refresh(username));
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/userEdit/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  userEdit(@PathVariable(value = "username") String username) {
		
		logger.info("edit"+username);
		
		User user = userService.selectUserDetail(username);
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/userEditProcess")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  userEditProcess(@Validated @RequestBody JoinRequest joinRequest) {
		
		User user = new User();
		
		user.setUsername(joinRequest.getUsername());
		user.setName(joinRequest.getName());
		user.setPhone(joinRequest.getPhone());

		userService.selectUserEdit(user);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PostMapping("/userLevelChange")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  userLevelChange(@Validated @RequestBody JoinRequest joinRequest) {
		
		User user = userService.selectUserDetail(joinRequest.getUsername());
		user.setAuthorities(joinRequest.getUserauth());
		System.out.println(user.getAuthorities());
		System.out.println(user.getAuthorities().size());
		userService.selectUserLevel(user);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/userDelete/{username}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  userDelete(@PathVariable(value = "username") String username) {
		
		logger.info("delete"+username);
		userService.deleteUser(username);
	
		return ResponseEntity.ok(username);
	}
	
	@GetMapping("/board/{pageNum}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  boardList(@PathVariable(value = "pageNum") int pageNum) {
		int page = pageNum;
		int count = boardService.boardCount();
		
		Board board = new Board();
		Pagination pagination = new Pagination();
		
		pagination.setPage(page);
		pagination.setCount(count);
		pagination.page();
		
		board.setPagination(pagination);
		
		List<Board> boardList = boardService.selectBoardList(board);
		
		boardList.get(0).setPagination(pagination);
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Content.";
	}
	
	@PostMapping("/boardWrite")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardWrite(@Validated @RequestBody BoardJoinRequest boardJoinRequest) {
		
		Board board = new Board();
	      
	      //유저 데이터 세팅
	      
	      board.setbTitle(boardJoinRequest.getbTitle());
	      board.setbContent(boardJoinRequest.getbContent());
	      board.setbWriter(boardJoinRequest.getbWriter());
	      board.setuId(boardJoinRequest.getuId());
	      
	      //유저 생성
	      boardService.createBoard(board);
	      
	      return ResponseEntity.ok(board);
	}
	
	@PostMapping("/upload")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardUpload(@RequestParam("file") List<MultipartFile> multipartFiles) {
		
		String uploadPath="/home/ec2-user/api/surveyvue/survey/dist/img";
		
		Upload upload = new Upload();
		
		for(MultipartFile file : multipartFiles) {
			if(!file.isEmpty()) {
				String originalName=file.getOriginalFilename();
				String fileExtension=originalName.substring(originalName.lastIndexOf("."));
				String uuid = UUID.randomUUID().toString();
				String uploadName = uuid +  fileExtension;
				String savefileName= uploadPath + File.separator + uuid + fileExtension;
				upload.setUpload(uploadName);;
				upload.setFileName(originalName);
				boardService.uploadInsert(upload);
				
				Path savePath= Paths.get(savefileName);
				try {
					file.transferTo(savePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		List<Upload> uplist = boardService.uploadList(upload);		
		//return new ResponseEntity<>("success", HttpStatus.OK);
		return ResponseEntity.ok(uplist);
	}
	
	@PostMapping("/uploadUpdate")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  uploadUpdate(@Validated @RequestBody BoardJoinRequest boardJoinRequest) {
		
		Upload upload = new Upload();
		upload.setbId(boardJoinRequest.getbId());
		
		for(int i=0; i<boardJoinRequest.getUpList().size(); i++) {
			upload.setlId((int)boardJoinRequest.getUpList().get(i));
			boardService.uploadUpdate(upload);
		}
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping("/boardDetail")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  boardDetail(@RequestParam int bId) {
		
		logger.info("///"+bId);
		
		Board board = boardService.selectBoardDetail(bId);
		return ResponseEntity.ok(board);
	}
	
	@GetMapping("/boardEdit/{bId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardEdit(@PathVariable(value = "bId") int bId) {
		
		logger.info("edit"+bId);
		
		Board board = boardService.selectBoardDetail(bId);
		return ResponseEntity.ok(board);
	}
	
	@PostMapping("/boardReplyProcess")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardReplyProcess(@Validated @RequestBody BoardJoinRequest boardJoinRequest) {
		
		Board board = new Board();
	      
	      //유저 데이터 세팅
	      
	      board.setbTitle(boardJoinRequest.getbTitle());
	      board.setbContent(boardJoinRequest.getbContent());
	      board.setbWriter(boardJoinRequest.getbWriter());
	      board.setbGroup(boardJoinRequest.getbGroup());
	      board.setbOrder(boardJoinRequest.getbOrder());
	      board.setbDepth(boardJoinRequest.getbDepth());
	      board.setbOrigin(boardJoinRequest.getbId());
	      
	      //유저 생성
	      boardService.createReply(board);
	      
	      return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PostMapping("/boardEditProcess")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardEditProcess(@Validated @RequestBody BoardJoinRequest boardJoinRequest) {
		
		Board board = new Board();
		
		board.setbId(boardJoinRequest.getbId());
		board.setbTitle(boardJoinRequest.getbTitle());
		board.setbContent(boardJoinRequest.getbContent());
		
		boardService.selectBoardEdit(board);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/boardDelete/{bId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardDelete(@PathVariable(value = "bId") int bId) {
		
		logger.info("delete"+bId);
		boardService.deleteBoard(bId);
		
		String uploadPath="/home/ec2-user/api/surveyvue/survey/dist/img";
		List<Upload> list = boardService.uploadRead(bId);
		
		for(int i=0; i<list.size(); i++) {
			String filename = list.get(i).getUpload();
			String filePath = uploadPath + "/" + filename;
			
			File file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}
		}
		
		boardService.uploadDelete(bId);
		
	
		return ResponseEntity.ok(bId);
	}
	
	@GetMapping("/comment/{checkId}/{pageNum}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  commentList(@PathVariable(value = "checkId") int checkId, @PathVariable(value = "pageNum") int pageNum) {
		int page = pageNum;
		
		Comment comment = new Comment();
		comment.setbId(checkId);
		int count = commentService.commentCount(comment);
		
		Pagination pagination = new Pagination();
		
		pagination.setPage(page);
		pagination.setCount(count);
		pagination.page();
		
		comment.setPagination(pagination);
		
		List<Comment> commentList = commentService.selectCommentList(comment);
		
		if( commentList.isEmpty() != true) {
			commentList.get(0).setPagination(pagination);
		}
		return ResponseEntity.ok(commentList);
	}
	
	@PostMapping("/commentWrite")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  commentWrite(@Validated @RequestBody CommentJoinRequest commentJoinRequest) {
		
		Comment comment = new Comment();
	      
	    comment.setcContent(commentJoinRequest.getcContent());
	    comment.setcWriter(commentJoinRequest.getcWriter());
	    comment.setbId(commentJoinRequest.getbId());
	    comment.setuId(commentJoinRequest.getuId());
	    
	    commentService.createComment(comment);
	    
	    Pagination pagination = new Pagination();
	    int page=1;
	    pagination.setPage(page);
	    comment.setPagination(pagination);
	    //return new ResponseEntity<>("success", HttpStatus.OK);
	    return ResponseEntity.ok(comment);
	
	}
	
	@PostMapping("/commentReply")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  commentReply(@Validated @RequestBody CommentJoinRequest commentJoinRequest) {
		
		Comment comment = new Comment();
	      
	    comment.setcContent(commentJoinRequest.getcContent());
	    comment.setcWriter(commentJoinRequest.getcWriter());
	    comment.setcId(commentJoinRequest.getcId());
	    comment.setbId(commentJoinRequest.getbId());
	    comment.setcOrigin(commentJoinRequest.getcId());
	    comment.setcGroup(commentJoinRequest.getcGroup());
	    comment.setcOrder(commentJoinRequest.getcOrder());
	    comment.setcDepth(commentJoinRequest.getcDepth());
	    
	    commentService.replyComment(comment);
	    
	    Pagination pagination = new Pagination();
	    int page=1;
	    pagination.setPage(page);
	    comment.setPagination(pagination);
	    //return new ResponseEntity<>("success", HttpStatus.OK);
	    return ResponseEntity.ok(comment);
	
	}
	
	@PostMapping("/commentEdit")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  commentEdit(@Validated @RequestBody CommentJoinRequest commentJoinRequest) {
		
		Comment comment = new Comment();
		
		comment.setcId(commentJoinRequest.getcId());
		comment.setbId(commentJoinRequest.getbId());
		comment.setcContent(commentJoinRequest.getcContent());
		
		commentService.selectCommentEdit(comment);
		
		Pagination pagination = new Pagination();
	    int page=1;
	    pagination.setPage(page);
	    comment.setPagination(pagination);
	    
	    return ResponseEntity.ok(comment);
		//return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/commentDelete/{cId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  commentDelete(@PathVariable(value = "cId") int cId) {
		
	    logger.info("delete"+cId);
		
		commentService.selectCommentDelete(cId);
		
		return ResponseEntity.ok(cId);
	}
	
	@GetMapping("/boardSearch/{searchcase}/{searchkeyword}/{pageNum}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  boardSearchList(@PathVariable(value = "searchcase") String searchcase, @PathVariable(value = "searchkeyword") String searchkeyword, @PathVariable(value = "pageNum") int pageNum) {
		
		Search search = new Search();

		int switchcase = 0;
		
		if (searchcase.equals("제목")) {
			switchcase = 1;
		} else if (searchcase.equals("제목+내용")) {
			switchcase = 2;
		} else if (searchcase.equals("작성자")) {
			switchcase = 3;
		}
		
		String keyword = "%"+searchkeyword+"%";
		
		search.setbSearch(switchcase);
		search.setbKeyword(keyword);
		
		int page = pageNum;
		int count = boardService.boardSearchCount(search);
		
		Pagination pagination = new Pagination();
		
		pagination.setPage(page);
		pagination.setCount(count);
		pagination.page();
		
		Board board = new Board();
		
		board.setPagination(pagination);
		board.setSearch(search);
		
		List<Board> boardList = boardService.boardSearchList(board);
		
		boardList.get(0).setPagination(pagination);
		search.setbSearchOrigin(searchcase);
		search.setbKeyword(searchkeyword);
		boardList.get(0).setSearch(search);
		return ResponseEntity.ok(boardList);
	}
	
	@PostMapping("/addSurvey")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  addSurvey(@Validated @RequestBody SurveyJoinRequest surveyJoinRequest) {
		
		Survey survey = new Survey();
		survey.setsTitle(surveyJoinRequest.getsTitle());
		survey.setsSubTitle(surveyJoinRequest.getsSubTitle());
		survey.setQuestionList(surveyJoinRequest.getQuestionList());
		survey.setsWriter(surveyJoinRequest.getsWriter());
		survey.setuId(surveyJoinRequest.getuId());
		
		surveyService.addSurvey(survey);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping("/survey/{pageNum}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  surveyList(@PathVariable(value = "pageNum") int pageNum) {
		int page = pageNum;
		int count = surveyService.surveyCount();
		
		Survey survey = new Survey();
		Pagination pagination = new Pagination();
		
		pagination.setPage(page);
		pagination.setCount(count);
		pagination.page();
		
		survey.setPagination(pagination);
		
		List<Survey> surveyList = surveyService.selectSurveyList(survey);
		
		surveyList.get(0).setPagination(pagination);
		return ResponseEntity.ok(surveyList);
	}
	
	@GetMapping("/surveyDetail")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  surveyDetail(@RequestParam int sId) {
		
		logger.info("///"+sId);
		
		Survey survey = surveyService.selectSurveyDetail(sId);
		
		return ResponseEntity.ok(survey);
	}
	
	@DeleteMapping("/surveyDelete/{sId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?>  surveyDelete(@PathVariable(value = "sId") int sId) {
		
		logger.info("delete"+sId);
		surveyService.surveyDelete(sId);
	
		return ResponseEntity.ok(sId);
	}
	
	@PostMapping("/surveyAnswer")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?>  surveyAnswer(@Validated @RequestBody AnswerJoinRequest answerJoinRequest) {
		
		Survey survey = new Survey();
		
		survey.setQuestionList(answerJoinRequest.getQuestionList());
		survey.setsId(answerJoinRequest.getsId());
		survey.setsWriter(answerJoinRequest.getaWriter());
		survey.setuId(answerJoinRequest.getuId());
		
		surveyService.addSurveyAnswer(survey);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping("/surveyReport")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  surveyReport(@RequestParam int sId) {
		
		Survey survey = new Survey();
		
		survey = surveyService.selectSurveyUser(sId);
		
		return ResponseEntity.ok(survey);
	}
	
	@GetMapping("/answerDetail")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  answerDetail(@RequestParam String username, @RequestParam int sId) {
		
		Survey survey = new Survey();
		survey.setsId(sId);
		survey.setsWriter(username);
		
		survey = surveyService.selectAnswerDetail(survey);
		
		return ResponseEntity.ok(survey);
	}
	
	@GetMapping("/questionChart")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  questionChart(@RequestParam int sId, @RequestParam int qId) {
		
		Answer answer = new Answer();
		answer.setqId(qId);
		answer.setsId(sId);
		
		List<Item> itemlist = surveyService.itemValueList(answer);
		
		List<Map<String, Object>> list = surveyService.answerCount(itemlist);
		
		return ResponseEntity.ok(list);
	}
}
