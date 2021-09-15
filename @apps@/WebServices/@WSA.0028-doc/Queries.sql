
-- �������� ������ �� ������������. DEST = 5000 - ��������
SELECT GOD, MC, IIF(SKLAD = '', CEX, SKLAD) AS DEST_POINT, NDOK, KDET, KTO, PLAVKA, MARKA, MEH_SV, HIM, KOLDET
FROM RASXOD WHERE DEST_POINT = 5000

-- ������������ ������ �������
SELECT distinct(trim(SKLAD)) AS SKLAD FROM RASXOD WHERE SKLAD <> NULL

-- ������������ ������ �����
SELECT distinct(trim(CEX)) AS CEX FROM RASXOD WHERE CEX <> NULL

