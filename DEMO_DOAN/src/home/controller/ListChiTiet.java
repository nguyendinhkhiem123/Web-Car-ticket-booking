package home.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.entity.Members;
import poly.entity.Tuyens;
import poly.entity.Ve;

@Transactional
@Controller
@RequestMapping(value = "/home/")
public class ListChiTiet {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "{id}/chitiet" , method = RequestMethod.GET)
	public String getList(ModelMap md , @PathVariable("id") int id) {
		Session  session1 = factory.getCurrentSession();
		String hql1 ="from Members s where s.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id", id);
		List<Members> ds1 = query1.list();
			
		Session session = factory.getCurrentSession();
		String hql = "from Ve s where s.idUser.iD=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<Ve> ds = query.list();
		
		if(ds.size() == 0) {
			md.addAttribute("id", id);
			md.addAttribute("hovaten",ds1.get(0).getHovaten());
			md.addAttribute("image", ds1.get(0).getImage());
			md.addAttribute("message","Bạn chưa đặt vé xe nào hết !!");
			return "user/list-order-erorr";
		}
		else
		{
			md.addAttribute("message","Danh sách vé xe bạn đã đặt là");
			md.addAttribute("id", id);
			md.addAttribute("hovaten",ds1.get(0).getHovaten());
			md.addAttribute("image", ds1.get(0).getImage());
			md.addAttribute("ds", ds);
			return "user/list-order";
			
		}
		
	} 
}
