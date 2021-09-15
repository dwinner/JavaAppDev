-- �������������� ��������� ���. ���������.
select nomtp from kattp;

-- ��������� ���. �������� �� ����������� ��������������.
select  nomtp, -- process id
        obozn_izd, -- figFG
        oboz_dse, -- figDSE
        date_cr, -- created date
        naim_dse -- nameDSE
from kattp
where nomtp = 14;
/* !������ ��������� ��� ���! */

-- ���� ��� ��������� - ������, ������ ������� - �������
select  nomtp,
        obozn_izd,
        oboz_dse,
        date_cr,
        naim_dse,
        nom_tip_tp
from kattp
where nomtp = 49 and nom_tip_tp <> 0

-- ������ �������� ��� ����������� ���. ��������.
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

-- ������ ��������� ��� ��������� ��������.
select  dob,      -- add time
        tst,      -- norm time
        naim,     -- step name
        nomper,   -- step num
        kvr       -- work type code
from "00000014.DBF" /* 00000014.PER */ /* nomop = 14 */
where nomop = 7; -- ����� ��������, ������� ����������� �������