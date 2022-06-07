create procedure setNextAction (
    farm_id varchar(50),
    field_no int(50),
    row_no int(50)
) BEGIN
declare activity varchar(50) default "";
declare next_activity varchar(50) default "sowing";
declare act_date date default CURRENT_DATE;
declare plant varchar(50) default "";
declare pl_unit varchar(50) default "";
select action into activity
from activities
where farmId = farm_id
    and field = field_no
    and row = row_no
order by date desc
limit 1;
select date into act_date
from activities
where farmId = farm_id
    and field = field_no
    and row = row_no
order by date desc
limit 1;
select type into plant
from activities
where farmId = farm_id
    and field = field_no
    and row = row_no
    and (
        action = "sowing"
        or action = "harvest"
    )
order by date desc
limit 1;
select unit into pl_unit
from activities
where farmId = farm_id
    and field = field_no
    and row = row_no
    and (
        action = "sowing"
        or action = "harvest"
    )
order by date desc
limit 1;
if activity = "sales" then
set next_activity = "sowing";
elseif activity = "sowing" then
set next_activity = "pesticide";
elseif activity = "pesticide" then
set next_activity = "fertilizer";
elseif activity = "fertilizer" then
set next_activity = "harvest";
elseif activity = "harvest" then
set next_activity = "sales";
end if;
select next_activity,
    act_date,
    plant,
    pl_unit;
end