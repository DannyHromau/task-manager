create user "manager";
alter user "manager" with PASSWORD 'manager';
create schema "task_manager";
alter schema "task_manager" owner to "manager";