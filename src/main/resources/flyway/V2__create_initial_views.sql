create or replace view lksg_tool.v_incidents as
  select row_number() OVER (ORDER BY req.id) AS i,
         req.id req_id,
         req.bpn,
         co.name co_name,
         res.job job_id,
         res.status job_status,
         res.impacted
    from lksg_tool.ess_incident_responses res,
         lksg_tool.ess_incident_requests req,
         lksg_tool.bpns,
         lksg_tool.companies co
   where res.request = req.id
     and req.bpn = bpns.bpn
     and co.id = req.company
order by co.name, req.bpn
;
-- -----------------------------------------------------------------------------
create or replace view lksg_tool.v_bpns as
select b.bpn,
       c.name company_name
  from lksg_tool.bpns b,
       lksg_tool.company_bpns cb,
       lksg_tool.companies c
 where b.bpn = cb.bpn
   and cb.company = c.id
;
-- -----------------------------------------------------------------------------
create or replace view lksg_tool.v_companies_bpns as
select company_id, company_name, street, nr, zip_code, location, country, bpns
from (select id as company_id,
             name as company_name, street, nr, zip_code, location, country,
             string_agg(bpn, ', ') as bpns
      from (select c.id, c.name, c.street, c.nr, c.zip_code, c.location, c.country,
                   cb.bpn
            from lksg_tool.companies c,
                 lksg_tool.company_bpns cb
            where c.id = cb.company) t
      group by 1, 2, 3, 4, 5, 6, 7) tt
order by 2
;
-- -----------------------------------------------------------------------------
create or replace view lksg_tool.v_impacts as
  select count(*) n,
         lksg_tool.v_incidents.impacted
    from lksg_tool.v_incidents
   where lksg_tool.v_incidents.impacted is not null
group by lksg_tool.v_incidents.impacted
;
create or replace view lksg_tool.v_dashborad_investigations as
select sum((impacted = 'Unknown')::int) status_unknown,
       sum((impacted = 'NO')::int) status_no,
       sum((impacted = 'YES')::int) status_yes
from lksg_tool.v_impacts as t
;
-- -----------------------------------------------------------------------------
-- create or replace view lksg_tool.v_dashborad_bpns as
-- ;
-- -----------------------------------------------------------------------------
-- create or replace view lksg_tool.v_dashborad_companies as
-- ;
