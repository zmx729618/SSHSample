package org.nercita.core.orm.hibernate;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

/**
* This class implements our custom Hibernate Dialect that fixes the mapping of
* Hibernate Type BIGINT (default is NUMERIC(19,0)) to a SQL BIGINT
* 
*/
public class InfocubeMSSQLDialect extends SQLServerDialect {
   
   public InfocubeMSSQLDialect() {
      super();
      registerColumnType( Types.BIGINT, "bigint" );   // Overwrite SQL Server datatype BIGINT
   }
}