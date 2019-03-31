package com.dabai.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dabai.springmvc.pojo.Items;
import com.dabai.springmvc.pojo.QueryVo;
import com.dabai.springmvc.service.ItemService;

@Controller
public class ItemController {

	@Resource(name="itemService")
	private ItemService itemService;
	
	
	@RequestMapping(value="/item/itemList.action")
	//返回itemList结果集
	public String itemList(Model model,HttpServletRequest request,HttpServletResponse response){
		List<Items>list=itemService.selectItemList();
		model.addAttribute("itemList", list);
		return "itemList";
	}
	//根据id修改
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView toEditItem(Integer id,HttpServletRequest request,HttpServletResponse response,Model model){
		Items items=itemService.selectItemsById(id);
		ModelAndView mov=new ModelAndView();
		mov.addObject("item", items);
		mov.setViewName("editItem");
		return mov;
		
		
	}
	//提交修改  参数为Items对象
	@RequestMapping(value="/updateItem.action")
	public String updateItem(QueryVo vo,MultipartFile pictureFile){
		//图片保存到
		String name=UUID.randomUUID().toString().replaceAll("-", "");
		String ext=FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		try {
			pictureFile.transferTo(new File("B:\\uploadTest\\"+name+"."+ext));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.getItems().setPic(name+"."+ext);
		itemService.updateItemsById(vo.getItems());
		/*ModelAndView mov=new ModelAndView();
		mov.setViewName("success");
		return "forward:/item/itemList.action";*/
		return "redirect:/itemEdit.action?id="+vo.getItems().getId();
		
		
	}
	//批量删除
	@RequestMapping(value="/updates.action",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updates(QueryVo vo){
		ModelAndView mov=new ModelAndView();
		mov.setViewName("success");
		return mov;
	}
	//json数据交互
	@RequestMapping(value="/json.action")
	public @ResponseBody Items json(@RequestBody Items items){
		
		System.out.println(items);
		return items;
	}
	//RestFul风格开发
	@RequestMapping(value="/itemEdit/{id}.action")
	public ModelAndView toEdit(@PathVariable Integer id,HttpServletRequest request,HttpServletResponse  response,Model model){
		Items items=itemService.selectItemsById(id);
		ModelAndView mov=new ModelAndView();
		mov.addObject("item", items);
		mov.setViewName("editItem");
		return mov;
	}
	//登陆
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String username,HttpSession session){
		session.setAttribute("username", username);
		return "redirect:/item/itemlist.action";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
