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

@Transactional
@Controller
@RequestMapping(value="admin")
public class ThongKe {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value="{id}/thongke", method = RequestMethod.GET)
	public String getThongKe(ModelMap md, @PathVariable("id") int id) {
		Session session1= factory.getCurrentSession();
		String hql1 = "FROM Members m where m.iD=:id";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("id",id);
		List<Members> ds1 = query1.list();
		md.addAttribute("id", ds1.get(0).getiD());
		md.addAttribute("hovaten", ds1.get(0).getHovaten());
		md.addAttribute("image", ds1.get(0).getImage());
		
		Session session = factory.getCurrentSession();
		String hql ="SELECT v.maTuyen.maTuyen,SUM(v.soLuongVe),v.maTuyen.tenXe,v.maTuyen.giaVe,v.maTuyen.soVe FROM Ve v GROUP BY v.maTuyen.maTuyen,v.maTuyen.tenXe,v.maTuyen.giaVe,v.maTuyen.soVe";
		Query query = session.createQuery(hql);
		List<Object[]> ds = query.list();
		if(ds.size() == 0) {
			md.addAttribute("message", "KHÔNG ĐỦ THÔNG TIN ĐỂ THỐNG KÊ");
			return "admin/thongke-erorr";
		}
		else {
		
			md.addAttribute("ds", ds);
		}
		return "admin/thongke";
	}
}
