package me.soulyana.demo.Controllers;

import me.soulyana.demo.Models.Education;
import me.soulyana.demo.Models.Person;
import me.soulyana.demo.Models.Skill;
import me.soulyana.demo.Models.WorkExp;
import me.soulyana.demo.Repositories.EducationRepository;
import me.soulyana.demo.Repositories.PersonRepository;
import me.soulyana.demo.Repositories.SkillRepository;
import me.soulyana.demo.Repositories.WorkExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    WorkExpRepository workExpRepository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("/")
    public String showHomepage(Model model) {
        model.addAttribute("menuoption", "homepage");
        return "homepage";
    }

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/addperson")
    public String addPersonForm(Model model) {
        model.addAttribute("menuoption", "addperson");
        model.addAttribute("aPerson", new Person());
        return "personform";
    }

    @PostMapping("/addperson")
    public String processPersonForm(@Valid @ModelAttribute("aPerson") Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "personform";
        }
        personRepository.save(person);
        return "redirect:/listpersons";
    }

    @RequestMapping("/listpersons")
    public String showListPersons(Model model) {
        model.addAttribute("menuoption", "listpersons");
        model.addAttribute("persons", personRepository.findAll());
        return "listpersons";
    }

    @RequestMapping("/updateperson")
    public String editPerson(HttpServletRequest request, Model model) {
        long personID = new Long(request.getParameter("id"));
        model.addAttribute("aPerson", personRepository.findById(personID).get());
        return "personform";
    }

    @RequestMapping("/addeducation")
    public String songForm(Model model, HttpServletRequest request) {
        //navbar
        model.addAttribute("menuoption", "addeducation");

        Education theEducation = new Education();

        model.addAttribute("anEducation", theEducation);
        model.addAttribute("persons", personRepository.findAll());
        return "educationform";
    }

    //update a song
    @RequestMapping("/updateeducation")
    public String editEducation(HttpServletRequest request, Model model) {
        long educationID = new Long(request.getParameter("id"));
        model.addAttribute("anEduation", educationRepository.findById(educationID).get());
        model.addAttribute("persons", personRepository.findAll());
        return "educationform";
    }

    @RequestMapping("/saveeducation")
    public String processEducationForm(@Valid @ModelAttribute("anEducation") Education education, BindingResult result) {
        if (result.hasErrors()) {
            return "educationform";
        }
        educationRepository.save(education);
        return "redirect:/";
    }

    @RequestMapping("/addskill")
    public String skillForm(Model model, HttpServletRequest request) {
        //navbar
        model.addAttribute("menuoption", "addskill");

        Skill theSkill = new Skill();

        model.addAttribute("aSkill", theSkill);
        model.addAttribute("persons", personRepository.findAll());
        return "skillform";
    }

    //update a song
    @RequestMapping("/updateskill")
    public String editSkill(HttpServletRequest request, Model model) {
        long skillID = new Long(request.getParameter("id"));
        model.addAttribute("aSkill", skillRepository.findById(skillID).get());
        model.addAttribute("persons", personRepository.findAll());
        return "skillform";
    }

    @RequestMapping("/saveskill")
    public String processSkillForm(@Valid @ModelAttribute("aSkill") Skill skill, BindingResult result) {
        if (result.hasErrors()) {
            return "skillform";
        }
        skillRepository.save(skill);
        return "redirect:/";
    }

    @RequestMapping("/addworkexp")
    public String workExpForm(Model model, HttpServletRequest request) {
        //navbar
        model.addAttribute("menuoption", "addworkexp");

        WorkExp theWorkExp = new WorkExp();

        model.addAttribute("aWorkExp", theWorkExp);
        model.addAttribute("persons", personRepository.findAll());
        return "workexpform";
    }

    @RequestMapping("/updateworkexp")
    public String editWorkExp(HttpServletRequest request, Model model) {
        long workExpID = new Long(request.getParameter("id"));
        model.addAttribute("aWorkExp", workExpRepository.findById(workExpID).get());
        model.addAttribute("persons", personRepository.findAll());
        return "workexpform";
    }

    @RequestMapping("/saveworkexp")
    public String processWorkExpForm(@Valid @ModelAttribute("aWorkExp") WorkExp workExp, BindingResult result) {
        if (result.hasErrors()) {
            return "workexpform";
        }
        workExpRepository.save(workExp);
        return "redirect:/";
    }
}
