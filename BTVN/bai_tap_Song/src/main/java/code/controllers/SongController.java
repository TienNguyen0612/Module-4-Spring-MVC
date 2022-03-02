package code.controllers;

import code.model.Category;
import code.model.Song;
import code.service.ICategoryService;
import code.service.ISongService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/song")
public class SongController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @Autowired
    private ISongService songService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView showSongs(@PageableDefault(value = 5) Pageable pageable, @RequestParam("search") Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("song/list");
        Page<Song> songs;
        if (search.isPresent()) {
            songs = songService.findAllByNameContaining(pageable, search.get());
            modelAndView.addObject("search", search.get());
        } else {
            songs = songService.findAllSongs(pageable);
        }
        modelAndView.addObject("file", view);
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("/create-song")
    public ModelAndView createSong() {
        ModelAndView modelAndView = new ModelAndView("song/create");
        Iterable<Category> categories = categoryService.findAllCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("song", new Song());
        return modelAndView;
    }

    @PostMapping("/save-song")
    public ModelAndView saveSong(@ModelAttribute Song song) {
        ModelAndView modelAndView = new ModelAndView("song/create");
        MultipartFile multipartFile = song.getSongFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(song.getSongFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        song.setSongUrl(fileName);
        Song songCreate = songService.save(song);
        if (songCreate != null) {
            Iterable<Category> categories = categoryService.findAllCategories();
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("message", "Add Song Successfully");
        }
        return modelAndView;
    }
}
