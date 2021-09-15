
-- Проверка записи на уникальность. DEST = 5000 - параметр
SELECT GOD, MC, IIF(SKLAD = '', CEX, SKLAD) AS DEST_POINT, NDOK, KDET, KTO, PLAVKA, MARKA, MEH_SV, HIM, KOLDET
FROM RASXOD WHERE DEST_POINT = 5000

-- Существующие номера складов
SELECT distinct(trim(SKLAD)) AS SKLAD FROM RASXOD WHERE SKLAD <> NULL

-- Существующие номера цехов
SELECT distinct(trim(CEX)) AS CEX FROM RASXOD WHERE CEX <> NULL

