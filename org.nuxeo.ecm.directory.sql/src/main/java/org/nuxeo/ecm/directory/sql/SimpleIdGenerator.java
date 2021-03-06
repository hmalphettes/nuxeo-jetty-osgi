/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.ecm.directory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.dialect.Dialect;
import org.nuxeo.ecm.directory.DirectoryException;
import org.nuxeo.ecm.directory.IdGenerator;
import org.nuxeo.ecm.directory.sql.repository.Select;
import org.nuxeo.ecm.directory.sql.repository.Table;
/**
 * This class implements a simple id generator.
 * <p>
 * It first queries the database to retrieve the maximum value for the id column,
 * then it returns.
 *
 * @author <a href="mailto:glefter@nuxeo.com">George Lefter</a>
 */
public class SimpleIdGenerator implements IdGenerator {

    private int nextId;

    public SimpleIdGenerator(Connection sqlConnection, Table table, Dialect dialect, String idColumn)
            throws DirectoryException {
        //String sql = "select max(" + idColumn + ") from " + tableName;
        Select select = new Select(dialect);
        select.setWhat("max(" + table.getColumn(idColumn).getQuotedName(dialect) + ")");
        select.setFrom(table.getQuotedName(dialect));
        String sql = select.getStatement();

        try {
            PreparedStatement ps = sqlConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt(1);
            } else {
                nextId = 0;
            }
            nextId++;
            rs.close();
        } catch (SQLException e) {
            throw new DirectoryException("nextIdGenerator retrieval failed", e);
        }
    }

    public int nextId() throws DirectoryException {
        if (nextId == -1) {
            throw new DirectoryException("Id generator not initialized");
        }
        return nextId++;
    }

}
