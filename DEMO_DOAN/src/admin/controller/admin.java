package admin.controller;

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

import poly.entity.Members;

@Transactional
@Controller
@RequestMapping("/admin/")
public class admin {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value ="home/{id}")
	public String admin(ModelMap md, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Members m where m.iD=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id",id);
		List<Members> ds = query.list();
		md.addAttribute("id", ds.get(0).getiD());
		md.addAttribute("hovaten", ds.get(0).getHovaten());
		md.addAttribute("image", ds.get(0).getImage());
		md.addAttribute("message","Xin chào Admin "+ ds.get(0).getHovaten() );
		return "admin/home";
	}
	
}

