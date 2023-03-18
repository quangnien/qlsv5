package com.qlsv5.controller;//package com.qlsv.controller;
//
//import com.qlsv.common.ReturnObject;
//import com.qlsv.dto.KhoaDto;
//import com.qlsv.service.KhoaService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/admin")
//public class KhoaController {
//
//    @Autowired
//    private KhoaService khoaService;
//
////    @PostMapping("/adddepartment")
////    @ResponseStatus(HttpStatus.CREATED)
////    public KhoaEntity createKhoa(@RequestBody KhoaDto khoa){
////        return khoaService.addKhoa(khoa);
////    }
//
//    @PostMapping("/khoa")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> createKhoa(@Valid @RequestBody KhoaDto khoa) throws IOException {
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Add Khoa!");
//
//            returnObject.setStatus(ReturnObject.INFO);
//            returnObject.setMessage("200");
//            Map<String, Object> mapData = new HashMap<>();
//
//            khoaService.addKhoa(khoa);
//            returnObject.setRetObj(khoa);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage("400");
//            returnObject.setMessage(ExceptionUtils.getStackTrace(ex));
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }
//
////    @GetMapping
////    public List<KhoaEntity> getKhoas() {
////        return khoaService.findAllKhoas();
////    }
//
//
////    @GetMapping("/{taskId}")
////    public KhoaEntity getKhoa(@PathVariable String taskId){
////        return service.getKhoaByKhoaId(taskId);
////    }
////
////    @GetMapping("/severity/{severity}")
////    public List<KhoaEntity> findKhoaUsingSeverity(@PathVariable int severity){
////        return service.getKhoaBySeverity(severity);
////    }
////
////    @GetMapping("/assignee/{assignee}")
////    public List<KhoaEntity> getKhoaByAssignee(@PathVariable String assignee){
////        return service.getKhoaByAssignee(assignee);
////    }
////
////    @PutMapping
////    public KhoaEntity modifyKhoa(@RequestBody KhoaEntity task){
////        return service.updateKhoa(task);
////    }
////
////    @DeleteMapping("/{taskId}")
////    public String deleteKhoa(@PathVariable String taskId){
////        return service.deleteKhoa(taskId);
////    }
//}
