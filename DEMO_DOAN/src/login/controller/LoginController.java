package login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.NEW;

import bean.LoginBean;
import poly.entity.Members;

@Transactional
@Controller
public class LoginController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap md, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}

		md.addAttribute("loginbean", new LoginBean());
		return "login/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login1(ModelMap md, @ModelAttribute("loginbean") LoginBean loginbean, BindingResult err,
			HttpServletRequest request, HttpServletResponse response
	)
	{
		
		if(loginbean.getUsername().length() == 0) {
			err.rejectValue("Username","loginbean", "Không được bỏ trống");
		}
		
		if(loginbean.getPassword().length() == 0) {
			err.rejectValue("Password", "loginbean", "Không được bỏ trống");
		}
		
		if(err.hasErrors()) {
			md.addAttribute("message","Lỗi đăng nhập. Hãy kiểm tra lỗi");
			return "login/login";
		}
		else {
			Session session = factory.getCurrentSession();
			String hql = "FROM Members m where m.userName=:user and m.passWord=:pass";
			Query query = session.createQuery(hql);
			query.setParameter("user", loginbean.getUsername());
			query.setParameter("pass", loginbean.getPassword());
			List<Members> ds = query.list();
			if(ds.size() == 0) {
				md.addAttribute("message","Tài khoản đã sai vui lòng nhập lại");
				return "login/login";
			}
			else {
				if(ds.get(0).getRole().equalsIgnoreCase("admin")) {
					md.addAttribute("id",ds.get(0).getiD());
					HttpSession sessionn = request.getSession();
					sessionn.setAttribute("user", ds.get(0).getiD());
					return "admin/index";
				}
				else {
					md.addAttribute("id",ds.get(0).getiD());
					HttpSession sessionn = request.getSession();
					sessionn.setAttribute("user", ds.get(0).getiD());
					return "user/index";
				}
			}
		}
		
	}
}
