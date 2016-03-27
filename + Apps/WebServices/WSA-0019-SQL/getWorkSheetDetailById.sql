/*--------------------------------------------------------------------------------
Операция:
getWorkSheetDetailById

Параметры операции:
			<xsd:element name="getWorkSheetDetailById">
						<xsd:element name="workSheetId" type="xsd:long"

Возвращает:
			<xsd:complexType name="WorkSheetDetail">
					<xsd:element name="workSheetNum" type="xsd:string"
					<xsd:element name="workSheetId" type="xsd:long"
					<xsd:element name="shiftJobId" type="xsd:long"
					<xsd:element name="shiftJobNum" type="xsd:string"
					<xsd:element name="wipEntityNum" type="xsd:string"
					<xsd:element name="department" type="xsd:string"
					<xsd:element name="site" type="xsd:string" minOccurs="1"
					<xsd:element name="shiftDate" type="xsd:dateTime"
					<xsd:element name="shiftNum" type="xsd:int" minOccurs="1"
					<xsd:element name="executor" type="tns:Worker"
					<xsd:element name="sheetLines" type="tns:SheetLines"

			<xsd:complexType name="SheetLine">
					<xsd:element name="sheetLineId" type="xsd:long"
					<xsd:element name="sheetLineNum" type="xsd:int"
					<xsd:element name="operationStdName" type="xsd:string"
					<xsd:element name="item" type="tns:Item" minOccurs="1"
					<xsd:element name="plannedQnt" type="xsd:double"
					<xsd:element name="acceptedQnt" type="xsd:double"
					<xsd:element name="rejectedQnt" type="xsd:double"
					<xsd:element name="uom" type="xsd:string" minOccurs="1"
					<xsd:element name="forNuclearEnergetics" minOccurs="0"
								<xsd:enumeration value="Для АЭС"></xsd:enumeration>
								<xsd:enumeration value="Не для АЭС"></xsd:enumeration>
					<xsd:element name="spz" type="xsd:string" minOccurs="0"
					<xsd:element name="re-Inspection" minOccurs="0"
								<xsd:enumeration value="С первого предъявления">
								<xsd:enumeration value="Со второго предъявления">
					<xsd:element name="guiltyDepartment" type="xsd:string"
					<xsd:element name="competenceStepValue" type="xsd:string"
					<xsd:element name="normTime" type="xsd:double"
					<xsd:element name="workDescription" type="xsd:string"
					<xsd:element name="competenceName" type="xsd:string"
					<xsd:element name="usedEquipment" type="tns:Equipment"

*/

-- Запрос возвращает данные заголовка наряда - Одна запись
SELECT
    worksheet_num,       -- <xsd:element name="workSheetNum" type="xsd:string"
    worksheet_id,        -- <xsd:element name="workSheetId" type="xsd:long"
    header_id,           -- <xsd:element name="shiftJobId" type="xsd:long"
    job_num,             -- <xsd:element name="shiftJobNum" type="xsd:string" 
    department_code,      -- <xsd:element name="department" type="xsd:string"
    site_code,           -- <xsd:element name="site" type="xsd:string" minOccurs="1" 
    job_date,            -- <xsd:element name="shiftDate" type="xsd:dateTime" 
    shift_num,           -- <xsd:element name="shiftNum" type="xsd:int" minOccurs="1"
    person_id,            -- <xsd:element name="executor" type="tns:Worker" --> personId
    employee_number,      -- <xsd:element name="executor" type="tns:Worker" --> tabNum    -- (4) 
    last_name,            -- <xsd:element name="executor" type="tns:Worker" --> lastName 
    first_name,           -- <xsd:element name="executor" type="tns:Worker" --> firstName 
    middle_names          -- <xsd:element name="executor" type="tns:Worker" --> middleName
FROM xxwsa0019_ws_headers_v
WHERE worksheet_id = 16




-- Запрос возвращает данные строк наряда - Одну или более записей
SELECT
    worksheet_id,
    line_id,        -- <xsd:element name="sheetLineId" type="xsd:long"
    line_number,    -- <xsd:element name="sheetLineNum" type="xsd:int"
    description,     -- <xsd:element name="operationStdName" type="xsd:string"
    item_no,          -- <xsd:element name="item" type="tns:Item" minOccurs="1" --> itemNum
    description_ru,   -- <xsd:element name="item" type="tns:Item" minOccurs="1" --> itemName
    figure,        -- <xsd:element name="item" type="tns:Item" minOccurs="1" --> drawNum
    planing_quantity,   -- <xsd:element name="plannedQnt" type="xsd:double"
    accepted, -- <xsd:element name="acceptedQnt" type="xsd:double"
    rejected,   -- <xsd:element name="rejectedQnt" type="xsd:double"
    primary_unit_of_measure,  -- <xsd:element name="uom" type="xsd:string" minOccurs="1"
    spz,            -- <xsd:element name="spz" type="xsd:string" minOccurs="0"
    defendant,      -- <xsd:element name="guiltyDepartment" type="xsd:string"
    competence_id,
    competence_name,    -- <xsd:element name="competenceName" type="xsd:string"
    resource_id,
    resource_code,      -- <xsd:element name="usedEquipment" type="tns:Equipment"
    norm_Time,              -- <xsd:element name="normTime" type="xsd:double"
    for_Nuclear,     -- <xsd:element name="forNuclearEnergetics" minOccurs="0"
    re_inspection,   -- <xsd:element name="re-Inspection" minOccurs="0"
    wip_entity_id,
    operation_seq_num,
    operation_desc,     -- <xsd:element name="workDescription" type="xsd:string"
    inventory_item_id,
    organization_id,
    competence_step_value   -- <xsd:element name="competenceStepValue" type="xsd:string"
FROM xxwsa0019_ws_lines_v
WHERE worksheet_id = 16    -- <xsd:element name="workSheetId" type="xsd:long"
ORDER BY line_number

