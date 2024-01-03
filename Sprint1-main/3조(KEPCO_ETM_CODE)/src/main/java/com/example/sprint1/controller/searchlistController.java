package com.example.sprint1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sprint1.dao.searchlistDao;

@Controller
public class searchlistController {
    @Autowired
    searchlistDao searchlistdao;

    @GetMapping("/searchlist")
    public String searchlist(
        @RequestParam(name = "hankook", required= false) String[] hankook,
        @RequestParam(name = "kumho", required= false) String[] kumho,
        @RequestParam(name = "nexen", required= false) String[] nexen,
        @RequestParam(name = "michelin", required= false) String[] michelin,
        @RequestParam(name = "continental", required= false) String[] continental,
        @RequestParam(name = "pcar", required= false) String[] pcar,
        @RequestParam(name = "suvcar", required= false) String[] suvcar,
        @RequestParam(name = "comfort", required= false) String[] comfort,
        @RequestParam(name = "sports", required= false) String[] sports,
        @RequestParam(name = "lownoise", required= false) String[] lownoise,
        @RequestParam(name = "125", required= false) String[] m125,
        @RequestParam(name = "205", required= false) String[] m205,
        @RequestParam(name = "215", required= false) String[] m215,
        @RequestParam(name = "225", required= false) String[] m225,
        @RequestParam(name = "235", required= false) String[] m235,
        @RequestParam(name = "245", required= false) String[] m245,
        @RequestParam(name = "255", required= false) String[] m255,
        @RequestParam(name = "265", required= false) String[] m265,
        @RequestParam(name = "275", required= false) String[] m275,
        @RequestParam(name = "285", required= false) String[] m285,
        @RequestParam(name = "305", required= false) String[] m305,
        @RequestParam(name = "30per", required= false) String[] per30,
        @RequestParam(name = "35per", required= false) String[] per35,
        @RequestParam(name = "40per", required= false) String[] per40,
        @RequestParam(name = "45per", required= false) String[] per45,
        @RequestParam(name = "50per", required= false) String[] per50,
        @RequestParam(name = "55per", required= false) String[] per55,
        @RequestParam(name = "60per", required= false) String[] per60,
        @RequestParam(name = "80per", required= false) String[] per80,
        @RequestParam(name = "13inch", required= false) String[] inch13,
        @RequestParam(name = "16inch", required= false) String[] inch16,
        @RequestParam(name = "17inch", required= false) String[] inch17,
        @RequestParam(name = "18inch", required= false) String[] inch18,
        @RequestParam(name = "19inch", required= false) String[] inch19,
        @RequestParam(name = "20inch", required= false) String[] inch20,
        @RequestParam(name = "21inch", required= false) String[] inch21,
        @RequestParam(name = "22inch", required= false) String[] inch22,
        Model model
    ) {
        if (suvcar != null) {
            suvcar = new String[]{"SUV, RV용"};
        }
        String[] allManufacturers = Stream.of(hankook, kumho, nexen, michelin, continental)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[] carTypes = Stream.of(pcar, suvcar)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[] tireChar = Stream.of(comfort, sports)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[] width = Stream.of(m125, m205, m215, m225, m235, m245, m255, m265, m275, m285, m305)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[] ratio = Stream.of(per30, per35, per40, per45, per50, per55, per60, per80)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[] inch = Stream.of(inch13, inch16, inch17, inch18, inch19, inch20, inch21, inch22)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);

        List<Map<String, Object>> tireList = searchlistdao.searchTires(
                allManufacturers, carTypes, tireChar, lownoise, width, ratio, inch
        );
        model.addAttribute("tire", tireList);

        return "/html/searchlist";
    }

}
