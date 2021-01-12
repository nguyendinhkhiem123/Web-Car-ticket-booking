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
import org.springframework.web.bind.annotation.RequestMethod;

import poly.entity.Members;
import poly.entity.Ve;

@Transactional
@Controller
@RequestMapping("/admin/")
public class ListBooking {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "{id}/list-book" , method = RequestMethod.GET)
	public String getListBook(ModelMap md, @PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session2= factory.getCurrentSession();
		String hql2 = "FROM Ve";
		Query query2 = session2.createQuery(hql2);
		List<Ve> ds2 = query2.list();
		md.addAttribute("ds", ds2);
		if(ds2.size() == 0) {
			md.addAttribute("message", "CHƯA CÓ VÉ XE ĐƯỢC ĐẶT");
			return "admin/list-booking-erorr";
		}
		return "admin/list-booking";
	}
}
