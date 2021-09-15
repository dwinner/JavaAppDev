/*
select * from xxodi.xxbi_plans;
select * from xxodi.xxbi_planpositions;
*/

-- ������� 1 (��). � ���. �����������: 0.015 (1.07% overheads to 1 row)
SELECT
  plans.plan_name as plan_name,
  plans.period as period,
  outer_tbl.figure as figure,   -- itemFigure
  outer_tbl.item as item,     -- itemCode
  outer_tbl.item_description as item_description   -- itemName
FROM
  xxodi.xxbi_planpositions outer_tbl,
  xxodi.xxbi_plans plans
WHERE EXISTS
  (SELECT 1 FROM xxodi.xxbi_plans inner_tbl
   WHERE inner_tbl.id = outer_tbl.id
    AND plan_name LIKE '0200-12-2-1-�02'
    AND period >= TO_DATE('01.04.2012', 'DD.MM.YYYY')
    AND period <= TO_DATE('01.04.2012', 'DD.MM.YYYY')
  )
;
   
-- ������� 2 (��). � ���������� ������������: 0.016
SELECT 
    plans.plan_name as plan_name,
    plans.period as period,
    outer_tbl.figure as figure,   -- itemFigure
    outer_tbl.item as item,     -- itemCode
    outer_tbl.item_description as item_description   -- itemName
FROM
    xxodi.xxbi_planpositions outer_tbl,
    xxodi.xxbi_plans plans
WHERE outer_tbl.id = plans.id
    AND plan_name LIKE '0200-12-2-1-�02' -- ���� ����� planName
    AND period >= TO_DATE('01.04.2012', 'DD.MM.YYYY') -- ���� ����� CompletionDateAfter
    AND period <= TO_DATE('01.04.2012', 'DD.MM.YYYY') -- ���� ����� CompletionDateBefore
;
