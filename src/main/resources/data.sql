-- 1.0 每日号码指标
drop table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_04;
create table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_04 as
select prov_name,city_name,count(distinct mobile) active_user_d
from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_01_02
where p_day_id=${DataTime}
group by prov_name,city_name
;

--1.0每日ip指标
--剔除手机号对应ip
drop table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_00;
create table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_00 as
select a.prov_name,a.city_name,a.ip
from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_02 a
where not exists
    (select 1 from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_01_02 b where a.ip=b.ip and b.p_day_id=${DataTime} and b.prov_name<>'其他')
  and a.p_day_id=${DataTime}
; --不剔除归属不到省的号码对应IP的数据。


select prov_name,city_name,ip
from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_00



--city_name <>'其他' (剔除一个ip对应多个city数据)
drop table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_01;
create table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_01 as
select a.prov_name,a.city_name,a.ip
from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_00 a
where a.city_name <>'其他'
;
--city_name ='其他'(剔除一个ip对应多个city数据)
drop table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_02;
create table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_02
as
select a.prov_name,a.city_name,a.ip
from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_00 a
where a.city_name ='其他'
  and not exists(select 1 from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_01 c where a.ip=c.ip)
;



--1.0日ip指标统计
drop table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05;
create table hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05
as
select t.prov_name,t.city_name, count(distinct t.ip) active_ip_d
from(select t1.prov_name,t1.city_name,t1.ip from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_01 t1
     union all
     select t2.prov_name,t2.city_name,t2.ip from hfx_temp.tmp_rhtx_dxxcx_mix_active_user_d_${DataTime}_05_02 t2
    ) t
group by t.prov_name,t.city_name
;