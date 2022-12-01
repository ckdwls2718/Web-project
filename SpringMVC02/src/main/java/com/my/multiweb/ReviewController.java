package com.my.multiweb;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.model.ReviewVO;
import com.shop.service.ReviewService;

import lombok.extern.log4j.Log4j;

/*Restfull 방식
 * - 메서드 방식에 따라 처리로직을 달리한다.
 * /reviews Get : 특정 상품에 대한리뷰목록을 조회
 * /reviews/1 Get : 특정 리뷰글을 조회
 * /reviews Post : 리뷰 글쓰기 처리
 * /reviews/1 Put : 특정 리뷰글을 수정
 * /reviews/1 Delete : 특정 리뷰글을 삭제
*/
@RestController
@Log4j
public class ReviewController {
	
	@Inject
	private ReviewService reviewService;
	
	@GetMapping(value = "/reviews", produces = "application/json")
	public List<ReviewVO> reviewList(HttpSession ses){
		int pnum = (int)ses.getAttribute("pnum");
		log.info("pnum === "+pnum);
		List<ReviewVO> arr = reviewService.listReview(pnum);
		return arr;
	}
	
	@GetMapping(value="/reviews/{num}",produces="application/json")
	   public ReviewVO getReview(@PathVariable("num") int num) {
	      ReviewVO vo=reviewService.getReview(num);
	      return vo;
	   }
	
	@PostMapping(value = "/user/reviews", produces="application/xml")
	public ModelMap reviewInsert(@RequestParam("mfilename") MultipartFile mf, @ModelAttribute("rvo") ReviewVO rvo, HttpSession ses) {
		log.info("rvo === "+rvo);
		
		ServletContext app = ses.getServletContext();
		//업로드 디렉토리 절대경로 얻기
		String upDir=app.getRealPath("/resources/review_images");
		
		log.info("upDir === "+upDir);
		
		File dir= new File(upDir);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		try {
			mf.transferTo(new File(upDir, mf.getOriginalFilename()));
			rvo.setFilename(mf.getOriginalFilename());
		} catch (Exception e) {
			log.error(e);
		}
		
		int n = reviewService.addReview(rvo);
		
		ModelMap map = new ModelMap();
		map.addAttribute("result", n);
		return map;
	}
	
	@DeleteMapping(value="user/reviews/{num}", produces="application/json")
	public ModelMap reviewDelete(@PathVariable("num") int num) {
		log.info("delete num === "+num);
		int n = reviewService.deleteReview(num);
		ModelMap map=new ModelMap();
		map.addAttribute("result", n);
		return map;	
	}
	
	
	

}
