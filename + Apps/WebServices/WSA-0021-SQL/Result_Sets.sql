-- Идентификаторы доступных тех. процессов.
select nomtp from kattp;

-- Заголовок тех. процесса по конкретному идентификатору.
select  nomtp, -- process id
        obozn_izd, -- figFG
        oboz_dse, -- figDSE
        date_cr, -- created date
        naim_dse -- nameDSE
from kattp
where nomtp = 14;
/* !Учесть диапазоны для дат! */

-- Если это множество - пустое, значит процесс - типовой
select  nomtp,
        obozn_izd,
        oboz_dse,
        date_cr,
        naim_dse,
        nom_tip_tp
from kattp
where nomtp = 49 and nom_tip_tp <> 0

-- Список операций для конкретного тех. процесса.
select  ncex,      -- department
        kobr,      -- equipment code
        mod,       -- equipment model
        naob,      -- equipment name
        tshk,      -- norm time
        nomop,     -- operation id
        naimop,    -- operation name
        nomerop,   -- operation number
        kopr       -- operation subtype
from "00000014.DBF" /* 00000014.OPE */ /* nomtp = 14 */;

-- Список переходов для кокретной операции.
select  dob,      -- add time
        tst,      -- norm time
        naim,     -- step name
        nomper,   -- step num
        kvr       -- work type code
from "00000014.DBF" /* 00000014.PER */ /* nomop = 14 */
where nomop = 7; -- Номер операции, которой принадлежит переход