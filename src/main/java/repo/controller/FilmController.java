// package repo.controller;

// import java.sql.Date;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.hibernate.Session;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import repo.models.*;
// import repo.services.*;
// import org.springframework.web.bind.annotation.RequestParam;

// import jakarta.servlet.http.HttpSession;

// @Controller 
// public class FilmController {

//     @Autowired
//     private FilmsService filmsService;

//     @Autowired
//     private CategorieService categorieService;

//     @Autowired
//     private TypeService typeService;

//     @GetMapping("/")
//     public String listeFilm(Model model) {
//         List <Film> allFilms = filmsService.getAll();
//         List <Type> allTypes = typeService.getAll();

//         model.addAttribute("allTypes", allTypes);
//         model.addAttribute("allFilms", allFilms);
//         return "home";
//     }

//     @PostMapping("/login")
//     public String checkLogin (  @RequestParam("name") String name,
//                                 @RequestParam("pwd") String pwd,
//                                 HttpSession session) {
        
//         if (name.equals("ITU") && pwd.equals("123")) {
//             session.setAttribute("connection", "1");
//             System.out.println("1");
//             return "redirect:/";
//         }               

//         System.out.println("2");
//         return "redirect:/login"; 
//     }

//     @GetMapping("/login")
//     public String login () {

//         return "login";
//     }

//     @GetMapping("/ajoutFilm")
//     public String preAjoutFilms(Model model, HttpSession session) {
//         List <Categorie> categories = categorieService.getAll();
//         List <Type> allType = typeService.getAll();

//         model.addAttribute("allType", allType);
//         model.addAttribute("allCategorie", categories);

//         if (session.getAttribute("connection") == null) {
//             return "redirect:/login";
//         }

//         return "ajoutFilm";
//     }

//     @PostMapping("/enregistrerFilm")
//     public String enregistrerFilm(  @RequestParam("titre") String titre,
//                                     @RequestParam("idFilm") int idFilm,
//                                     @RequestParam("categories") List<Integer> categories, 
//                                     @RequestParam("type") String idType, 
//                                     @RequestParam("date_sortie") Date date_sortie) {
    
//         List <Categorie> cat = new ArrayList <> (); 

//         for (int i=0;i<categories.size();i++) {
//             cat.add(categorieService.getCategorieById(categories.get(i)).get());
//         }

//         if (idFilm == -1) {
//             Type type = typeService.getTypeById(Integer.parseInt(idType)).orElse(null);
//             Film newFilms = new Film(titre, type, date_sortie, cat);
//             filmsService.save(newFilms);
//         } else {
//             Type type = typeService.getTypeById(Integer.parseInt(idType)).orElse(null);
//             Film newFilms = new Film(idFilm, titre, type, date_sortie, cat);
//             filmsService.save(newFilms);
//         }
        
//         return "redirect:/";
//     }


//     @GetMapping("/modifFilm")
//     public String preModifFilms(Model model, @RequestParam("id") int id, HttpSession session) {
//         Film film = filmsService.getFilmById(id).orElse(null);

//         if (session.getAttribute("connection") == null) {
//             return "redirect:/login";    
//         }

//         List <Categorie> categories = categorieService.getAll();
//         Type type = typeService.getTypeById(film.getType().getId()).orElse(null);
//         List <Type> allType = typeService.getAll();

//         model.addAttribute("allType", allType);
//         model.addAttribute("film", film);
//         model.addAttribute("type", type);
//         model.addAttribute("allCategorie", categories);
//         return "ajoutFilm";
//     }

//     @GetMapping("/supprimerFilm")
//     public String supprimerFilms (@RequestParam("id") int idFilm, HttpSession session) {
        
//         if (session.getAttribute("connection") == null) {
//             return "redirect:/login";
//         }
        
//         filmsService.delete(idFilm);
//         return "redirect:/";
//     }

// }