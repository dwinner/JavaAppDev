/*--------------------------------------------------------------------------------
Операция:
findWorkSheetListByProfile

Параметры операции:
			<xsd:element name="findWorkSheetListByProfile">
						<xsd:element name="workSheetNum" type="xsd:string"
						<xsd:element name="department" type="xsd:string"
						<xsd:element name="shiftNum" type="xsd:int" minOccurs="0"
						<xsd:element name="afterDate" type="xsd:dateTime"
						<xsd:element name="beforeDate" type="xsd:dateTime"
						<xsd:element name="employeeTabNum" type="xsd:string"
						<xsd:element name="employeeLastName" type="xsd:string"

Возвращает:
			<xsd:complexType name="WorkSheetSummary">
					<xsd:element name="workSheetId" type="xsd:long"
					<xsd:element name="workSheetNum" type="xsd:string"
					<xsd:element name="shiftDate" type="xsd:dateTime"
					<xsd:element name="shiftNum" type="xsd:int" minOccurs="1"
					<xsd:element name="executorName" type="xsd:string"

*/

SELECT
    ws.worksheet_id,            -- <xsd:element name="workSheetId" type="xsd:long"
    ws.worksheet_num,           -- <xsd:element name="workSheetNum" type="xsd:string"
    sj.job_date,            -- <xsd:element name="shiftDate" type="xsd:dateTime"
    sj.shift_num,           -- <xsd:element name="shiftNum" type="xsd:int" minOccurs="1"
    p.full_name,            -- <xsd:element name="executorName" type="xsd:string"
    d.department_code,
    p.employee_number
FROM
    xxtpa_mfg0015_worksheets ws,
    xxtpa_mfg0003_headers sj,
    per_all_people_f p,
    bom_departments_v d
WHERE sj.header_id = ws.header_id
AND p.person_id = ws.executor_id
AND d.department_id = sj.department_id
AND sj.job_date BETWEEN p.effective_start_date AND p.effective_end_date
-- условия из параметров вызова операции:
AND ws.worksheet_num LIKE '%1'      -- <xsd:element name="workSheetNum" type="xsd:string"
AND d.department_code LIKE '08%'    -- <xsd:element name="department" type="xsd:string"
AND sj.shift_num = 1                -- <xsd:element name="shiftNum" type="xsd:int" minOccurs="0"
AND sj.job_date > TO_DATE('31.12.2010','DD.MM.YYYY')    -- <xsd:element name="afterDate" type="xsd:dateTime"
AND sj.job_date < TO_DATE('31.12.2014','DD.MM.YYYY')    -- <xsd:element name="beforeDate" type="xsd:dateTime"
AND p.employee_number LIKE '08%'    -- <xsd:element name="employeeTabNum" type="xsd:string"
AND UPPER(p.full_name) LIKE '%Ч%'
ORDER BY worksheet_num
