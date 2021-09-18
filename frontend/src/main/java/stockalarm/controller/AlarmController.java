package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stockalarm.AlarmClient;
import stockalarm.to.AlarmDTO;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AlarmController {

    private final AlarmClient alarmClient;

    @GetMapping("/alarms")
    public String userAlarms(final Model model) {
        final List<AlarmDTO> alarms = alarmClient.getAlarmsByUser("");
//        model.addAttribute("alarms", alarmClient.getAlarmsByUser(principal.getName()));
        model.addAttribute("alarms", alarms);
        model.addAttribute("alarmUpdate", new AlarmDTO());
        return "user/alarms";
    }

    @PostMapping("/alarms")
    public String createAlarm(@ModelAttribute("alarm") final AlarmDTO alarmDTO) {
        log.info("Create alarm: {}", alarmDTO);
        alarmClient.createAlarm(alarmDTO);

        return "redirect:/user/alarms";
    }

    @GetMapping("/alarms/delete/{id}")
    public String deleteAlarm(@PathVariable final String id) {
        log.info("Delete alarm with id: {}", id);
        alarmClient.deleteAlarm(id);

        return "redirect:/user/alarms";

    }

    @PostMapping("/alarms/update")
    public String updateAlarm(@ModelAttribute("alarmUpdate") final AlarmDTO alarmDTO) {
        // TODO
        log.info("Update alarm to: {}", alarmDTO);
        alarmClient.updateAlarm(alarmDTO);

        return "redirect:/user/alarms";

    }
}
