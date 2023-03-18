package com.qlsv5.service.impl;

import com.qlsv5.service.DiemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DiemServiceImpl implements DiemService {
//    private final SystemConfig systemConfig;
//    private final ServletContext servletContext;
//    private final LpgwMappingBankBinProcRepository lpgwMappingBankBinProcRepository;
//    private final LpgwMasterDataLogService masterDataLogService;
//    private final JcaConstantDisplayService constantDisplayService;
//
//    @Autowired
//    LpgwMasterDataLogService lpgwMasterDataLogService;
//
//    @Override
//    public PageWrapper<MappingBankBinProcSearchResultsDto> searchMappingBankBinProc(int page, int pageSize, MappingBankBinProcSearchDto searchDto) {
//        PageWrapper<MappingBankBinProcSearchResultsDto> pageWrapper = new PageWrapper<>();
//        int sizeOfPage = systemConfig.settingPageSizeList(pageSize, pageWrapper, page);
//        List<MappingBankBinProcSearchResultsDto> result = new ArrayList<>();
//        int count = lpgwMappingBankBinProcRepository.countSearchMappingBankBinProc(searchDto);
//        if (count > 0) {
//            int offSet = (page - 1) * sizeOfPage;
//            result = lpgwMappingBankBinProcRepository.searchMappingBankBinProc(offSet, sizeOfPage, searchDto);
//        }
//        pageWrapper.setDataAndCount(result, count);
//        return pageWrapper;
//    }
//
//    @Override
//    @SneakyThrows
//    public MappingBankBinProcDto findOneMappingBankBinProcById(Long id) {
//        Optional<LpgwMappingBankBinProcEntity> optionalEntity = lpgwMappingBankBinProcRepository.findOneMappingBankBinProcById(id);
//        if (optionalEntity.isPresent()) {
//            LpgwMappingBankBinProcEntity entity = optionalEntity.get();
//
//            MappingBankBinProcDto mappingBankBinProDto = new MappingBankBinProcDto();
//            mappingBankBinProDto.setLpgwMappingBankBinProcId(entity.getLpgwMappingBankBinProcId());
//            mappingBankBinProDto.setBinNoModifying(entity.getBinNo());
//            mappingBankBinProDto.setCitadBankCodeModifying(entity.getCitadBankCode());
//            mappingBankBinProDto.setBankNameModifying(entity.getBankName());
//            mappingBankBinProDto.setBinTypeModifying(entity.getBinType());
//            mappingBankBinProDto.setProcessStatusModifying(entity.getProcessStatus());
//            mappingBankBinProDto.setCardNameModifying(entity.getCardName());
//            mappingBankBinProDto.setStatusModifying(entity.getStatus());
//            mappingBankBinProDto.setFunctionCode(LpgwMasterDataLogConst.MAPPING_BANK_BIN);
//            mappingBankBinProDto.setCreateDate(entity.getCreateDate());
//            mappingBankBinProDto.setCreatedBy(entity.getCreatedBy());
//            mappingBankBinProDto.setUpdatedBy(entity.getUpdatedBy());
//
//            // Check to show Status when status new and checker is not current login user
//            mappingBankBinProDto.setLccStatus(getLccStatus(mappingBankBinProDto));
//
//            return mappingBankBinProDto;
//        } else {
//            throw new NotFoundException("Mapping bank bin with id " + id + " not found in database!");
//        }
//    }
//
//    @Override
//    public MappingBankBinProcDto findRelativeMappingBankBinById(Long id) {
//        MappingBankBinProcDto result = lpgwMappingBankBinProcRepository.findRelativeMappingBankBinById(id);
//        return result;
//    }
//
//    @Override
//    public void writeToExcelAutoFlush(MappingBankBinProcSearchDto searchDto, HttpServletResponse res, String matKhau)
//            throws IOException, InvalidFormatException, GeneralSecurityException {
//
//        String templateName = "mapping_bank_bin.xlsx";
//        String template = servletContext.getRealPath(CommonConstant.REAL_PATH_TEMPLATE_EXCEL) + File.separator + templateName;
//
//        OPCPackage pkg = OPCPackage.open(new FileInputStream(new File(template)));
//
//        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(pkg)) {
//            List<MappingBankBinSearchResultsDto> results = lpgwMappingBankBinProcRepository.exportExcel(searchDto);
//            int lengthRow = 1;
//            if (CollectionUtils.isNotEmpty(results)) {
//                lengthRow = results.size();
//            }
//
//            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, lengthRow);
//            SXSSFSheet sheetData = sxssfWorkbook.getSheet("data");
//
//            ExcelHelper excelHelper = new ExcelHelper();
//
//            CellStyle cellStyleCenter;
//            cellStyleCenter = sxssfWorkbook.createCellStyle();
//            cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
//
//            CellStyle cellStyleLeft;
//            cellStyleLeft = sxssfWorkbook.createCellStyle();
//            cellStyleLeft.setAlignment(HorizontalAlignment.LEFT);
//
//            CellStyle cellStyleRight;
//            cellStyleRight = sxssfWorkbook.createCellStyle();
//            cellStyleRight.setAlignment(HorizontalAlignment.RIGHT);
//
//            //format number decimal
//            CreationHelper createHelper = xssfWorkbook.getCreationHelper();
//            DataFormat dataFormat = createHelper.createDataFormat();
//            CellStyle cellStyleRightDec = sxssfWorkbook.createCellStyle();
//            cellStyleRightDec.setAlignment(HorizontalAlignment.RIGHT);
//            cellStyleRightDec.setDataFormat(dataFormat.getFormat("#,##0"));
//
//            CellReference landmark = new CellReference("A3");
//            int startRow = landmark.getRow();
//            for (MappingBankBinSearchResultsDto item : results) {
//                int colnum = 0;
//                int numberCount = startRow - 1;
//                excelHelper.fillCell(sheetData, startRow, colnum++, numberCount, cellStyleRight);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBinNoCurrent() != null ? item.getBinNoCurrent() : "", cellStyleRight);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getCitadBankCodeCurrent() != null ? item.getCitadBankCodeCurrent() : "", cellStyleCenter);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBankNameCurrent() != null ? item.getBankNameCurrent() : "", cellStyleLeft);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBinTypeCurrent() != null ? item.getBinTypeCurrent() : "", cellStyleLeft);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getStatusCurrent() != null ? item.getStatusCurrent() : "", cellStyleLeft);
//
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getProcessStatus() != null ? item.getProcessStatus() : "", cellStyleLeft);
//
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBinNoModifying() != null ? item.getBinNoModifying() : "", cellStyleRight);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getCitadBankCodeModifying() != null ? item.getCitadBankCodeModifying() : "", cellStyleCenter);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBankNameModifying() != null ? item.getBankNameModifying() : "", cellStyleLeft);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getBinTypeModifying() != null ? item.getBinTypeModifying() : "", cellStyleLeft);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getStatusModifying() != null ? item.getStatusModifying() : "", cellStyleLeft);
//
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getCreateDate() != null ? DateFormatUtils.format(item.getCreateDate(), systemConfig.getConfig(SystemConfig.DATE_PATTERN)) : "", cellStyleCenter);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getUpdatedBy() != null ? item.getUpdatedBy() : "", cellStyleLeft);
//                excelHelper.fillCell(sheetData, startRow, colnum++, item.getUpdateDate() != null ? DateFormatUtils.format(item.getUpdateDate(), systemConfig.getConfig(SystemConfig.DATE_PATTERN)) : "", cellStyleCenter);
//                startRow++;
//            }
//
//            excelHelper.writeOutputWorkbook(sxssfWorkbook, res, "MAPPING_BANK_BIN", searchDto.getPassExport(), true);
//        }
//        catch (Exception e){
//            throw e;
//        }
//    }
//
//    @Override
//    public void saveOrUpdate(MappingBankBinProcDto masterData, Boolean isSubmit, Boolean isAddNew) {
//
//        StringBuilder descriptions = new StringBuilder();
//
//        if (masterData.getLpgwMappingBankBinProcId() != null) {
//
//            MappingBankBinProcDto dtoDb = this.findOneMappingBankBinProcById(masterData.getLpgwMappingBankBinProcId());
//
//            if ((StringUtils.isNotBlank(masterData.getBinNoModifying()) || StringUtils.isNotBlank(dtoDb.getBinNoModifying()))
//                    && !StringUtils.equals(masterData.getBinNoModifying(), dtoDb.getBinNoModifying())) {
//                descriptions.append("Bin No modify : " + "From ")
//                        .append(dtoDb.getBinNoModifying()).append(" to ")
//                        .append(masterData.getBinNoModifying()).append(". ");
//            }
//            if ((StringUtils.isNotBlank(masterData.getCitadBankCodeModifying()) || StringUtils.isNotBlank(dtoDb.getCitadBankCodeModifying()))
//                    && !StringUtils.equals(masterData.getCitadBankCodeModifying(), dtoDb.getCitadBankCodeModifying())) {
//                descriptions.append("Citad bank code modify : " + "From ")
//                        .append(dtoDb.getCitadBankCodeModifying()).append(" to ")
//                        .append(masterData.getCitadBankCodeModifying()).append(". ");
//            }
//            if ((StringUtils.isNotBlank(masterData.getBankNameModifying()) || StringUtils.isNotBlank(dtoDb.getBankNameModifying()))
//                    && !StringUtils.equals(masterData.getBankNameModifying(), dtoDb.getBankNameModifying())) {
//                descriptions.append("Bank Name modify : " + "From ")
//                        .append(dtoDb.getBankNameModifying()).append(" to ")
//                        .append(masterData.getBankNameModifying()).append(". ");
//            }
//            if ((StringUtils.isNotBlank(masterData.getBinTypeModifying()) || StringUtils.isNotBlank(dtoDb.getBinTypeModifying()))
//                    && !StringUtils.equals(masterData.getBinTypeModifying(), dtoDb.getBinTypeModifying())) {
//                descriptions.append("Bank type modify : " + "From ")
//                        .append(dtoDb.getBinTypeModifying()).append(" to ")
//                        .append(masterData.getBinTypeModifying()).append(". ");
//            }
//            if ((StringUtils.isNotBlank(masterData.getCardNameModifying()) || StringUtils.isNotBlank(dtoDb.getCardNameModifying()))
//                    && !StringUtils.equals(masterData.getCardNameModifying(), dtoDb.getCardNameModifying())) {
//                descriptions.append("Card name modify : " + "From ")
//                        .append(dtoDb.getCardNameModifying()).append(" to ")
//                        .append(masterData.getCardNameModifying()).append(". ");
//            }
//            if ((StringUtils.isNotBlank(masterData.getStatusModifying()) || StringUtils.isNotBlank(dtoDb.getStatusModifying()))
//                    && !StringUtils.equals(masterData.getStatusModifying(), dtoDb.getStatusModifying())) {
//                descriptions.append("Status modify : " + "From ")
//                        .append(dtoDb.getStatusModifying() != null? StatusEnum.getNameByValue(Integer.parseInt(dtoDb.getStatusModifying())) : "null").append(" to ")
//                        .append(StatusEnum.getNameByValue(Integer.parseInt(masterData.getStatusModifying()))).append(". ");
//            }
//        }
//
//        LpgwMappingBankBinProcEntity entity = new LpgwMappingBankBinProcEntity();
//        entity.setLpgwMappingBankBinProcId(masterData.getLpgwMappingBankBinProcId());
//        entity.setBinNo(masterData.getBinNoModifying());
//        entity.setCitadBankCode(masterData.getCitadBankCodeModifying());
////        entity.setStatus(masterData.isNew() ? CommonConstant.STATUS_NEW : masterData.getStatusModifying());
//        entity.setStatus(masterData.isNew() ? CommonConstant.STATUS_ACTIVE : masterData.getStatusModifying());
//        entity.setBankName(masterData.getBankNameModifying());
//        entity.setBinType(masterData.getBinTypeModifying());
//        entity.setCardName(masterData.getCardNameModifying());
//        entity.setCreatedBy(masterData.isNew() ? UserProfileUtils.getUserNameLogin() : masterData.getCreatedBy());
//        entity.setCreateDate(masterData.isNew() ? new Date() : masterData.getCreateDate());
//        entity.setUpdatedBy(masterData.isNew() ? null : UserProfileUtils.getUserNameLogin());
//        entity.setUpdateDate(masterData.isNew() ? null : new Date());
//
//        /* set PROCESS_STATUS -> 00 */
//        if(isSubmit == true){
//            entity.setProcessStatus(MasterDataProcessStatusConstant.WAITING_CHECKER);
//        }
//        else if(isSubmit == false){
//            entity.setProcessStatus(MasterDataProcessStatusConstant.MAKER_PREPARING);
//        }
//
//        lpgwMappingBankBinProcRepository.save(entity);
//
//        String actionCheck = masterData.getAction();
//        if(actionCheck.equals(ActionCodeConstant.ACTION_ADD)){
//            String action= ActionCodeConstant.ACTION_ADD;
//            masterDataLogService
//                    .saveMasterDataLog(entity.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN, action, "", "", "" );
//        }
//        else if(actionCheck.equals(ActionCodeConstant.ACTION_EDIT)){
//            String action= ActionCodeConstant.ACTION_EDIT;
//            masterDataLogService
//                    .saveMasterDataLog(entity.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN, action, "", "", descriptions.toString());
//        }
//        else if(actionCheck.equals(ActionCodeConstant.ACTION_SUBMIT)){
//            String action = ActionCodeConstant.ACTION_SUBMIT;
//            masterDataLogService
//                    .saveMasterDataLog(entity.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN, action, "", "", descriptions.toString());
//        }
//    }
//
//    @Override
//    public MappingBankBinProcDto getMappingBankBinByBinNoAndCidCode(String binNo, String citadBankCode) {
//        return lpgwMappingBankBinProcRepository.getMappingBankBinByBinNoAndCidCode(binNo, citadBankCode);
//    }
//
//    @Override
//    public void rejectMappingBankBinProc(MappingBankBinProcDto masterData) {
//
//        LpgwMappingBankBinProcEntity entity = new LpgwMappingBankBinProcEntity();
//        entity.setLpgwMappingBankBinProcId(masterData.getLpgwMappingBankBinProcId());
//        entity.setBinNo(masterData.getBinNoModifying());
//        entity.setCitadBankCode(masterData.getCitadBankCodeModifying());
//        entity.setStatus(masterData.getStatusModifying());
//        entity.setBankName(masterData.getBankNameModifying());
//        entity.setBinType(masterData.getBinTypeModifying());
//        entity.setCardName(masterData.getCardNameModifying());
//        entity.setCreatedBy(masterData.getCreatedBy());
//        entity.setCreateDate(masterData.getCreateDate());
//        entity.setUpdatedBy(UserProfileUtils.getUserNameLogin());
//        entity.setUpdateDate(new Date());
//
//        entity.setProcessStatus(MasterDataProcessStatusConstant.REJECTED);
//
//        lpgwMappingBankBinProcRepository.save(entity);
//
//        String action = ActionCodeConstant.ACTION_REJECT;
//
//        masterDataLogService
//                .saveMasterDataLog(entity.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN, action, "", "", "");
//    }
//
//    @Override
//    public List<Select2Dto> getLccStatus(MappingBankBinProcDto mappingBankBinProcDto) {
//
//        String status = StringUtils.isNotBlank(mappingBankBinProcDto.getStatusModifying()) ? mappingBankBinProcDto.getStatusModifying() : "";
//        String createdBy = StringUtils.isNotBlank(mappingBankBinProcDto.getCreatedBy()) ? mappingBankBinProcDto.getCreatedBy() : "";
//
//        List<Select2Dto> lccStatus = constantDisplayService.getLccStatus();
//        lccStatus.removeIf(listStatus -> StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_NEW));
//
//        if (StringUtils.isBlank(status) || mappingBankBinProcDto.isNew()) {
//
//            lccStatus.removeIf(listStatus -> StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_IN_ACTIVE));
//
//        }
//
////        if (StringUtils.isBlank(status) || mappingBankBinProcDto.isNew()) {
////            lccStatus.removeIf(listStatus -> StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_ACTIVE)
////                    || StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_IN_ACTIVE));
////
////        } else if (!StringUtils.equals(CommonConstant.STATUS_NEW, status)) {
////
////            lccStatus.removeIf(listStatus -> StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_NEW));
////
////        } else if (StringUtils.equals(CommonConstant.STATUS_NEW, status)
////                && StringUtils.equals(UserProfileUtils.getUserNameLogin(), createdBy)) {
////
////            lccStatus.removeIf(listStatus -> StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_ACTIVE)
////                    || StringUtils.equals(listStatus.getId(), CommonConstant.STATUS_IN_ACTIVE));
////        }
//
//        return lccStatus;
//    }
//
//    @Override
//    public void setParameters(MappingBankBinProcSearchDto searchDto) {
////        searchDto.setSelect2Status(StatusEnum.getSelect2ComboList());
//        searchDto.setSelect2Status(StatusEnum.getSelect2ComboList1Layer());
//        searchDto.setSelect2BinType(MappingBankBinProcTypeEnum.getSelect2ComboList());
//        searchDto.setSelect2ProcessStatus(MasterDataProcStatusEnum.getSelect2ComboList());
//        searchDto.setFunctionCode(LpgwMasterDataLogConst.MAPPING_BANK_BIN);
//    }
//
//    @Override
//    public int checkMappingBankBinProcExists(String binNo, String citadBankCode, Long id) {
//        int count = lpgwMappingBankBinProcRepository.checkMappingBankBinProcExists(binNo, citadBankCode, id);
//        return count;
//    }
//
//    @Override
//    public void doApprove(MappingBankBinProcDto searchDto) {
//
//        MappingBankBinProcDto dataRelative = this.findRelativeMappingBankBinById(searchDto.getLpgwMappingBankBinProcId());
//
//        String userName = UserProfileUtils.getUserNameLogin();
//        dataRelative.setUpdatedBy(userName);
//
//        if(dataRelative.getLpgwMappingBankBinId() != null){
//            /* insert new history */
//            lpgwMappingBankBinProcRepository.backUpHistory(dataRelative.getLpgwMappingBankBinId());
//
//            /* update mapping bank bin */
//            lpgwMappingBankBinProcRepository.upDateMappingBankBin(dataRelative);
//        }
//        else {
//            /* update mapping bank bin */
//            lpgwMappingBankBinProcRepository.insertMappingBankBin(dataRelative);
//        }
//
//        /* update processStatus on LPGW_MAPPING_BANK_BIN_PROC */
//        String APPROVED = MasterDataProcessStatusConstant.APPROVED;
//        lpgwMappingBankBinProcRepository.approveBtn(dataRelative, APPROVED);
//
//        lpgwMasterDataLogService.saveMasterDataLog(searchDto.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN,
//                ActionCodeConstant.ACTION_APPROVE, "", CommonConstant.APPROVED_MASTER_RULE, "");
//    }
//
////    @Override
////    public void sendForApproval(MappingBankBinProcSearchDto searchDto) {
////
////        MappingBankBinProcSearchDto existInfo = lpgwMappingBankBinProcRepository.getMappingBankBinProcEditById(searchDto.getLpgwMappingBankBinProcId());
////        if (existInfo.getStatus() != null) {
////            throw new BusinessException("Can not send for approval due invalid status");
////        }
////
////        LpgwMappingBankBinProcEntity mappingBankBinProcEntity = new LpgwMappingBankBinProcEntity();
////        mappingBankBinProcEntity.setBankName(searchDto.getBankNameModifying());
////        mappingBankBinProcEntity.setBinType(searchDto.getBinTypeModifying());
////        mappingBankBinProcEntity.setCitadBankCode(searchDto.getCitadBankCodeModifying());
////        mappingBankBinProcEntity.setBinNo(searchDto.getBinNoModifying());
////        mappingBankBinProcEntity.setCardName(searchDto.getCardNameModifying());
////
////        mappingBankBinProcEntity.setStatus(CommonConstant.IN_PROGRESS_MASTER_RULE);
////
////        mappingBankBinProcEntity.setCreatedBy(UserProfileUtils.getUserNameLogin());
////        mappingBankBinProcEntity.setCreateDate(new Date());
////        lpgwMappingBankBinProcRepository.save(mappingBankBinProcEntity);
////
////        // TODO : NHỚ GHI LOG TẠM THỜI CMT
////        String changeDesc = "Change value from GHI LOG";
////       /* if(existInfo.getbankname)
////        StringBuilder changeDesc = "Change value from ["
////        StringBuilder changeDesc = "Change value from [" + existInfo.() + "] to [" + searchDto.getSettingValueModifying() + "]";*/
////        lpgwMasterDataLogService.saveMasterDataLog(searchDto.getLpgwMappingBankBinProcId(), LpgwMasterDataLogConst.MAPPING_BANK_BIN,
////                ActionCodeConstant.ACTION_EDIT, "", CommonConstant.IN_PROGRESS_MASTER_RULE, changeDesc);
////
////    }
//
////    @Override
////    public List<LpgwMappingBankBinProcEntity> findAllUrlHealthCheckByPrefixKey(String prefixKey) {
////        return lpgwMappingBankBinProcRepository.findAllUrlHealthCheckByPrefixKey(prefixKey);
////    }
}
