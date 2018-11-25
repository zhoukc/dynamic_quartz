package club.javaprod.quartz.dynamic_quartz.api;

import club.javaprod.quartz.dynamic_quartz.model.ResponseOutput;
import club.javaprod.quartz.dynamic_quartz.service.QuartzService;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "定时任务")
@RestController
@RequestMapping("/api")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;


    @GetMapping(value = "modify", produces = "application/json")
    public ResponseOutput modifyQuartz(String name, String group, String cron) {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.modifyQuartz(name, group, cron);
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }


    @GetMapping(value = "getJobList", produces = "application/json")
    public ResponseOutput getJobList() {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        responseOutput.setCode(0);
        try {
            List<QuartzService.JobInfo> jobList = quartzService.getJobList();
            responseOutput.setData(jobList);
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setCode(1);
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }


    @GetMapping(value = "pauseAllJob", produces = "application/json")
    public ResponseOutput pauseAllJob() {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }

    @GetMapping(value = "pauseJob", produces = "application/json")
    public ResponseOutput pauseJob(String name, String group) {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }


    @GetMapping(value = "resumeAllJob", produces = "application/json")
    public ResponseOutput resumeAllJob() {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.resumeAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }

    @GetMapping(value = "resumeJob", produces = "application/json")
    public ResponseOutput resumeJob(String name, String group) {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.resumeJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }


    @GetMapping(value = "deleteJob", produces = "application/json")
    public ResponseOutput deleteJob(String name, String group) {
        ResponseOutput responseOutput = new ResponseOutput();
        responseOutput.setMsg("success");
        try {
            quartzService.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
            responseOutput.setMsg("fail");
        }
        return responseOutput;
    }


}
