package com.dao.page;


public class SendInspectObject implements
        java.io.Serializable {

    /**
     *
     */
    public SendInspectObject() {

    }

    // 送检单ID
    private Object sendInspectId;
    //送检单编号
    private Object sendInspectNumber;
    // 送检单类型
    private Object sendInspectType;
    // 工程名称
    private Object projectName;
    // 工程地址
    private Object inspectNature;
    // 组数
    private Object groupCount;
    // 是否复查
    private Object reexamination;
    // 检测性质
    private Object whetherRecheck;
    // 见证人
    private Object wiTnessMan;
    // 见证编号
    private Object wiTnessNumber;
    // 见证单位
    private Object wiTnessUnit;
    // 进场数量
    private Object entranceNumber;
    // 送检单位entranceNumber
    private Object sendInspectNuit;
    // 送检日期
    private Object sendInspectDate;
    // 样品编号
    private Object sampleReportId;
    // 样品名称
    private Object sampleName;
    // 生产厂家
    private Object manufacturer;
    // 工程部位
    private Object projectPart;
    // 规格型号
    private Object specificationModel;
    // 批号
    private Object federalApprova;
    // 代表量
    private Object representativeMeasure;
    // 数量
    private Object amount;
    // 取样日期
    private Object samplingDate;
    //送样人
    public Object sendInspectLinkMan;
    //送样人电话
    public Object sendInspectPhone;

    //		<钢筋种类>热轧带肋钢筋</钢筋种类>
    private Object typeSteelBar;
    //		<牌号>HRB335</牌号>
    private Object grade;
    //		<钢筋等级>A</钢筋等级>
    private Object steelGrade;
    //		<公称直径>1</公称直径>
    private Object nominalDiameter;
    //		<出厂日期>2015-10-9 20:32:49</出厂日期>
    private Object productionDate;
    //		<炉号>2015-0001</炉号>
    private Object furnaceNumber;
    //		<是否经过冷拉调直>是</是否经过冷拉调直>
    private Object whetheArfterColdDrawingAndStraightening;
    //		<接头数量>2</接头数量>
    private Object jointNumber;
    //		<焊接类型>焊接类型</焊接类型>
    private Object weldingType;
    //		<预埋件T型接头>预埋件T型接头</预埋件T型接头>
    private Object embeddedTypeTJoint;
    //		<焊条焊剂型号>焊条焊剂型号</焊条焊剂型号>
    private Object electrodeFluxModel;
    //		 <焊工姓名>张三</焊工姓名>
    private Object welderName;
    //		<焊工证号>No.123</焊工证号>
    private Object welderId;
    //		 <母材牌号>HRB335</母材牌号>
    private Object baseMetalGrade;
    //		<接头等级>xxx</接头等级>
    private Object jointGrade;
    //		 <连接材料>xxx</连接材料>
    private Object connectMaterialScience;
    //		<检验类型>xxx</检验类型>
    private Object testType;
    //		<养护温度>aaa</养护温度>
    private Object curingTemperature;
    //		<强度等级>一级</强度等级>
    private Object strengthGrade;
    //		<抗渗等级>xx</抗渗等级>
    private Object antiPermeabilityGrade;
    //		<成型日期>2015-10-9 20:33:17</成型日期>
    private Object formingDate;
    //		<试件尺寸>1x1x0.5</试件尺寸>
    private Object specimenSize;
    //		<要求龄期>3</要求龄期>
    private Object requirementAge;
    //		<养护条件>养护条件</养护条件>
    private Object curingCondition;
    //		<规格尺寸>XXXXXXX </规格尺寸>
    private Object specificationsSize;
    //		<密度等级>XXXXXXX </密度等级>;
    private Object densityGrade;
    //		<产品类别>XXXXXXX </产品类别>
    private Object productCategory;
    //		<产品等级>XXXXXXX </产品等级>
    private Object productGrade;
    //		<密度级别>XXXXXXX </密度级别>
    private Object densityLevel;
    //		<产品标记>XXXXXXX </产品标记>
    private Object productSign;
    //		<生产单位> XXXXXXX </生产单位>
    private Object productionUnit;
    //		<取样部位> XXXXXXX </取样部位>
    private Object samplingPosition;
    //		<样品种类> XXXXXXX </样品种类>
    private Object sampleType;
    //		<商标>xxx</商标>
    private Object trademark;
    //		 <生产日期> XXXXXXX </生产日期>
    private Object manufactureDate;
    //		<厚度>xxx</厚度>
    private Object thickness;
    //		<样品类别> XXXXXXX </样品类别>
    private Object sampleSategory;
    //		<备注>xxx</备注>
    private Object remarks;
    //		<上表面隔离材料>xxx</上表面隔离材料>
    private Object upperSurfaceIsolatingMaterial;
    //		<下表面隔离材料>xxx</下表面隔离材料>
    private Object lowerSurfaceIsolatingMaterial;
    //		<胎基>xxx</胎基>
    private Object tyreBase;
    //		<标号>XXXXXXX</标号>
    private Object tagNumber;
    //		<产地> XXXXXXX </产地>
    private Object PlaceOrigin;
    //		<设计等级>XXXXXXX</设计等级>
    private Object designGrade;
    // <见证时间>2018-02-27 11:28:04</见证时间>
    private Object witnessDate;
    // <见证员>李四</见证员>
    private Object witnessPerson;

    public Object getSendInspectId() {
        return sendInspectId;
    }

    public void setSendInspectId(Object sendInspectId) {
        this.sendInspectId = sendInspectId;
    }

    public Object getSendInspectNumber() {
        return sendInspectNumber;
    }

    public void setSendInspectNumber(Object sendInspectNumber) {
        this.sendInspectNumber = sendInspectNumber;
    }

    public Object getSendInspectType() {
        return sendInspectType;
    }

    public void setSendInspectType(Object sendInspectType) {
        this.sendInspectType = sendInspectType;
    }

    public Object getProjectName() {
        return projectName;
    }

    public void setProjectName(Object projectName) {
        this.projectName = projectName;
    }

    public Object getInspectNature() {
        return inspectNature;
    }

    public void setInspectNature(Object inspectNature) {
        this.inspectNature = inspectNature;
    }

    public Object getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Object groupCount) {
        this.groupCount = groupCount;
    }

    public Object getReexamination() {
        return reexamination;
    }

    public void setReexamination(Object reexamination) {
        this.reexamination = reexamination;
    }

    public Object getWhetherRecheck() {
        return whetherRecheck;
    }

    public void setWhetherRecheck(Object whetherRecheck) {
        this.whetherRecheck = whetherRecheck;
    }

    public Object getWiTnessMan() {
        return wiTnessMan;
    }

    public void setWiTnessMan(Object wiTnessMan) {
        this.wiTnessMan = wiTnessMan;
    }

    public Object getWiTnessNumber() {
        return wiTnessNumber;
    }

    public void setWiTnessNumber(Object wiTnessNumber) {
        this.wiTnessNumber = wiTnessNumber;
    }

    public Object getWiTnessUnit() {
        return wiTnessUnit;
    }

    public void setWiTnessUnit(Object wiTnessUnit) {
        this.wiTnessUnit = wiTnessUnit;
    }

    public Object getEntranceNumber() {
        return entranceNumber;
    }

    public void setEntranceNumber(Object entranceNumber) {
        this.entranceNumber = entranceNumber;
    }

    public Object getSendInspectNuit() {
        return sendInspectNuit;
    }

    public void setSendInspectNuit(Object sendInspectNuit) {
        this.sendInspectNuit = sendInspectNuit;
    }

    public Object getSendInspectDate() {
        return sendInspectDate;
    }

    public void setSendInspectDate(Object sendInspectDate) {
        this.sendInspectDate = sendInspectDate;
    }

    public Object getSampleReportId() {
        return sampleReportId;
    }

    public void setSampleReportId(Object sampleReportId) {
        this.sampleReportId = sampleReportId;
    }

    public Object getSampleName() {
        return sampleName;
    }

    public void setSampleName(Object sampleName) {
        this.sampleName = sampleName;
    }

    public Object getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Object manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Object getProjectPart() {
        return projectPart;
    }

    public void setProjectPart(Object projectPart) {
        this.projectPart = projectPart;
    }

    public Object getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(Object specificationModel) {
        this.specificationModel = specificationModel;
    }

    public Object getFederalApprova() {
        return federalApprova;
    }

    public void setFederalApprova(Object federalApprova) {
        this.federalApprova = federalApprova;
    }

    public Object getRepresentativeMeasure() {
        return representativeMeasure;
    }

    public void setRepresentativeMeasure(Object representativeMeasure) {
        this.representativeMeasure = representativeMeasure;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Object getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(Object samplingDate) {
        this.samplingDate = samplingDate;
    }

    public Object getSendInspectLinkMan() {
        return sendInspectLinkMan;
    }

    public void setSendInspectLinkMan(Object sendInspectLinkMan) {
        this.sendInspectLinkMan = sendInspectLinkMan;
    }

    public Object getSendInspectPhone() {
        return sendInspectPhone;
    }

    public void setSendInspectPhone(Object sendInspectPhone) {
        this.sendInspectPhone = sendInspectPhone;
    }

    public Object getTypeSteelBar() {
        return typeSteelBar;
    }

    public void setTypeSteelBar(Object typeSteelBar) {
        this.typeSteelBar = typeSteelBar;
    }

    public Object getGrade() {
        return grade;
    }

    public void setGrade(Object grade) {
        this.grade = grade;
    }

    public Object getSteelGrade() {
        return steelGrade;
    }

    public void setSteelGrade(Object steelGrade) {
        this.steelGrade = steelGrade;
    }

    public Object getNominalDiameter() {
        return nominalDiameter;
    }

    public void setNominalDiameter(Object nominalDiameter) {
        this.nominalDiameter = nominalDiameter;
    }

    public Object getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Object productionDate) {
        this.productionDate = productionDate;
    }

    public Object getFurnaceNumber() {
        return furnaceNumber;
    }

    public void setFurnaceNumber(Object furnaceNumber) {
        this.furnaceNumber = furnaceNumber;
    }

    public Object getWhetheArfterColdDrawingAndStraightening() {
        return whetheArfterColdDrawingAndStraightening;
    }

    public void setWhetheArfterColdDrawingAndStraightening(
            Object whetheArfterColdDrawingAndStraightening) {
        this.whetheArfterColdDrawingAndStraightening = whetheArfterColdDrawingAndStraightening;
    }

    public Object getJointNumber() {
        return jointNumber;
    }

    public void setJointNumber(Object jointNumber) {
        this.jointNumber = jointNumber;
    }

    public Object getWeldingType() {
        return weldingType;
    }

    public void setWeldingType(Object weldingType) {
        this.weldingType = weldingType;
    }

    public Object getEmbeddedTypeTJoint() {
        return embeddedTypeTJoint;
    }

    public void setEmbeddedTypeTJoint(Object embeddedTypeTJoint) {
        this.embeddedTypeTJoint = embeddedTypeTJoint;
    }

    public Object getElectrodeFluxModel() {
        return electrodeFluxModel;
    }

    public void setElectrodeFluxModel(Object electrodeFluxModel) {
        this.electrodeFluxModel = electrodeFluxModel;
    }

    public Object getWelderName() {
        return welderName;
    }

    public void setWelderName(Object welderName) {
        this.welderName = welderName;
    }

    public Object getWelderId() {
        return welderId;
    }

    public void setWelderId(Object welderId) {
        this.welderId = welderId;
    }

    public Object getBaseMetalGrade() {
        return baseMetalGrade;
    }

    public void setBaseMetalGrade(Object baseMetalGrade) {
        this.baseMetalGrade = baseMetalGrade;
    }

    public Object getJointGrade() {
        return jointGrade;
    }

    public void setJointGrade(Object jointGrade) {
        this.jointGrade = jointGrade;
    }

    public Object getConnectMaterialScience() {
        return connectMaterialScience;
    }

    public void setConnectMaterialScience(Object connectMaterialScience) {
        this.connectMaterialScience = connectMaterialScience;
    }

    public Object getTestType() {
        return testType;
    }

    public void setTestType(Object testType) {
        this.testType = testType;
    }

    public Object getCuringTemperature() {
        return curingTemperature;
    }

    public void setCuringTemperature(Object curingTemperature) {
        this.curingTemperature = curingTemperature;
    }

    public Object getStrengthGrade() {
        return strengthGrade;
    }

    public void setStrengthGrade(Object strengthGrade) {
        this.strengthGrade = strengthGrade;
    }

    public Object getAntiPermeabilityGrade() {
        return antiPermeabilityGrade;
    }

    public void setAntiPermeabilityGrade(Object antiPermeabilityGrade) {
        this.antiPermeabilityGrade = antiPermeabilityGrade;
    }

    public Object getFormingDate() {
        return formingDate;
    }

    public void setFormingDate(Object formingDate) {
        this.formingDate = formingDate;
    }

    public Object getSpecimenSize() {
        return specimenSize;
    }

    public void setSpecimenSize(Object specimenSize) {
        this.specimenSize = specimenSize;
    }

    public Object getRequirementAge() {
        return requirementAge;
    }

    public void setRequirementAge(Object requirementAge) {
        this.requirementAge = requirementAge;
    }

    public Object getCuringCondition() {
        return curingCondition;
    }

    public void setCuringCondition(Object curingCondition) {
        this.curingCondition = curingCondition;
    }

    public Object getSpecificationsSize() {
        return specificationsSize;
    }

    public void setSpecificationsSize(Object specificationsSize) {
        this.specificationsSize = specificationsSize;
    }

    public Object getDensityGrade() {
        return densityGrade;
    }

    public void setDensityGrade(Object densityGrade) {
        this.densityGrade = densityGrade;
    }

    public Object getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Object productCategory) {
        this.productCategory = productCategory;
    }

    public Object getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(Object productGrade) {
        this.productGrade = productGrade;
    }

    public Object getDensityLevel() {
        return densityLevel;
    }

    public void setDensityLevel(Object densityLevel) {
        this.densityLevel = densityLevel;
    }

    public Object getProductSign() {
        return productSign;
    }

    public void setProductSign(Object productSign) {
        this.productSign = productSign;
    }

    public Object getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(Object productionUnit) {
        this.productionUnit = productionUnit;
    }

    public Object getSamplingPosition() {
        return samplingPosition;
    }

    public void setSamplingPosition(Object samplingPosition) {
        this.samplingPosition = samplingPosition;
    }

    public Object getSampleType() {
        return sampleType;
    }

    public void setSampleType(Object sampleType) {
        this.sampleType = sampleType;
    }

    public Object getTrademark() {
        return trademark;
    }

    public void setTrademark(Object trademark) {
        this.trademark = trademark;
    }

    public Object getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Object manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Object getThickness() {
        return thickness;
    }

    public void setThickness(Object thickness) {
        this.thickness = thickness;
    }

    public Object getSampleSategory() {
        return sampleSategory;
    }

    public void setSampleSategory(Object sampleSategory) {
        this.sampleSategory = sampleSategory;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public Object getUpperSurfaceIsolatingMaterial() {
        return upperSurfaceIsolatingMaterial;
    }

    public void setUpperSurfaceIsolatingMaterial(
            Object upperSurfaceIsolatingMaterial) {
        this.upperSurfaceIsolatingMaterial = upperSurfaceIsolatingMaterial;
    }

    public Object getLowerSurfaceIsolatingMaterial() {
        return lowerSurfaceIsolatingMaterial;
    }

    public void setLowerSurfaceIsolatingMaterial(
            Object lowerSurfaceIsolatingMaterial) {
        this.lowerSurfaceIsolatingMaterial = lowerSurfaceIsolatingMaterial;
    }

    public Object getTyreBase() {
        return tyreBase;
    }

    public void setTyreBase(Object tyreBase) {
        this.tyreBase = tyreBase;
    }

    public Object getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(Object tagNumber) {
        this.tagNumber = tagNumber;
    }

    public Object getPlaceOrigin() {
        return PlaceOrigin;
    }

    public void setPlaceOrigin(Object placeOrigin) {
        PlaceOrigin = placeOrigin;
    }

    public Object getDesignGrade() {
        return designGrade;
    }

    public void setDesignGrade(Object designGrade) {
        this.designGrade = designGrade;
    }

    public Object getWitnessDate() {
        return witnessDate;
    }

    public void setWitnessDate(Object witnessDate) {
        this.witnessDate = witnessDate;
    }

    public Object getWitnessPerson() {
        return witnessPerson;
    }

    public void setWitnessPerson(Object witnessPerson) {
        this.witnessPerson = witnessPerson;
    }


}
		
		
