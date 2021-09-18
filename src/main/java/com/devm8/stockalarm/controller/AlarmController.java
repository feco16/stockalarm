package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.model.dto.AlarmDTO;
import com.devm8.stockalarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/alarms")
    public String userAlarms(final Model model, final Principal principal) {
        model.addAttribute("alarms", alarmService.getAlarmsByUser(principal.getName()));
        model.addAttribute("alarmUpdate", new AlarmDTO());
        return "/alarms";
    }

    @PostMapping("/alarms")
    public String createAlarm(@ModelAttribute("alarm") final AlarmDTO alarmDTO) {
        log.info("Create alarm: {}", alarmDTO);
        alarmService.createAlarm(alarmDTO);

        return "redirect:/alarms";
    }

    @GetMapping("/alarms/delete/{id}")
    public String deleteAlarm(@PathVariable final String id) {
        log.info("Delete alarm with id: {}", id);
        alarmService.deleteAlarm(id);

        return "redirect:/alarms";

    }

    @PostMapping("/alarms/update")
    public String updateAlarm(@ModelAttribute("alarmUpdate") final AlarmDTO alarmDTO) {
        // TODO
        log.info("Update alarm to: {}", alarmDTO);
        alarmService.updateAlarm(alarmDTO);

        return "redirect:/user/alarms";

    }
}
