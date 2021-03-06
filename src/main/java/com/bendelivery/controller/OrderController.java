package com.bendelivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bendelivery.domain.CartVO;
import com.bendelivery.domain.MemberVO;
import com.bendelivery.domain.OrderMenuVO;
import com.bendelivery.domain.OrderVO;
import com.bendelivery.domain.RestaurantVO;
import com.bendelivery.service.CartService;
import com.bendelivery.service.OrderMenuService;
import com.bendelivery.service.OrderService;
import com.bendelivery.service.RestaurantService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Inject
	private CartService cart_service;
	@Inject
	private OrderService order_service;
	@Inject
	private OrderMenuService order_menu_service;
	@Inject 
	private RestaurantService res_service;
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<Integer> order(@RequestBody OrderVO vo, HttpServletRequest request)throws Exception{
		
		ResponseEntity<Integer> entity = null;
		order_service.createOrder(vo);
		System.out.println("오더 넘버는 " + vo.getOrder_no());
		try {
			entity = new ResponseEntity<Integer>(vo.getOrder_no(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
		// 주문 완료 시에 해당 식당-세션에 담긴 장바구니 내용 삭제 
		cart_service.deleteById(request.getSession().getId());
		// 세션에 등록된 식당번호 삭제
		request.getSession().removeAttribute("res_no");
		return entity;
	}
	
	@RequestMapping(value="/{order_no}", method = RequestMethod.POST )
	public ResponseEntity<String> order(@RequestBody OrderMenuVO vo)throws Exception{
		ResponseEntity<String> entity = null;
		
		order_menu_service.createOrderMenu(vo);
		try {
			entity = new ResponseEntity<String>("GOOD", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<OrderVO>> getOrderList(HttpServletRequest request, Model model)throws Exception {
		ResponseEntity<List<OrderVO>> entity = null;
		MemberVO vo = (MemberVO) request.getSession().getAttribute("login");
		System.out.println(vo.getMember_id());
		try {
			entity = new ResponseEntity<List<OrderVO>>(order_service.list((String)	vo.getMember_id()), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<OrderVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 해당 오더 번호의 메뉴 가져옴 
	@RequestMapping(value="/{order_no}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderMenuVO>> getOrderMenuList(@PathVariable int order_no, Model model)throws Exception {
		ResponseEntity<List<OrderMenuVO>> entity = null;
		try {
			entity = new ResponseEntity<List<OrderMenuVO>>(order_menu_service.list(order_no), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<OrderMenuVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// MyPage에서 식당 이름 가져오기 위함 
	@RequestMapping(value="/getRes/{res_no}", method = RequestMethod.GET)
	public ResponseEntity<RestaurantVO> getRes(@PathVariable int res_no)throws Exception {
		ResponseEntity<RestaurantVO> entity = null;
		RestaurantVO vo = res_service.read(res_no);
		System.out.println(vo.toString());
		try {
			entity = new ResponseEntity<RestaurantVO>(res_service.read(res_no), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<RestaurantVO>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 리뷰 추가 -> review_status : none -> exist 
	@RequestMapping(value="/addReview", method = RequestMethod.POST)
	public ResponseEntity<String> updateReviewStatus(@RequestBody OrderVO vo){
		ResponseEntity<String> entity = null;
		try {
			order_service.updateForReview(vo.getOrder_no());
			entity = new ResponseEntity<String>("REVIEWUPDATE", HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// Owner 페이지에서 주문 확인하기 위한 메소드
	@RequestMapping(value="/list/{res_no}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderVO>> listByRes(@PathVariable int res_no)throws Exception {
		ResponseEntity<List<OrderVO>> entity = null;
		System.out.println(res_no);
		try {
			entity = new ResponseEntity<List<OrderVO>>(order_service.listByRes(res_no), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<OrderVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// Owner 페이지에서 주문 확인하기 위한 메소드
	@RequestMapping(value="/ownerlist", method = RequestMethod.POST)
	public ResponseEntity<List<OrderVO>> listByResAndStatus(@RequestBody OrderVO vo)throws Exception {
		ResponseEntity<List<OrderVO>> entity = null;
		System.out.println(vo);
		try {
			entity = new ResponseEntity<List<OrderVO>>(order_service.listByResAndStatus(vo), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<OrderVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 주문 접수 
	@RequestMapping(value="/goIng", method = RequestMethod.PUT)
	public ResponseEntity<String> updateIng(@RequestBody OrderVO vo) throws Exception{
		ResponseEntity<String> entity = null;
		try {
			order_service.updateIng(vo.getOrder_no());
			entity = new ResponseEntity<String>("COMPLETE_ING", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 완료  
	@RequestMapping(value="/goDone", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDone(@RequestBody OrderVO vo) throws Exception {			
		ResponseEntity<String> entity = null;
		try {
			order_service.updateDone(vo.getOrder_no());
			entity = new ResponseEntity<String>("COMPLETE_DONE", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
