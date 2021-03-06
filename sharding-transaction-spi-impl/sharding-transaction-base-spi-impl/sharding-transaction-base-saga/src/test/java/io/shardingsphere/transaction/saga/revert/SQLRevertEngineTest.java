/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.shardingsphere.transaction.saga.revert;

import io.shardingsphere.transaction.saga.SagaBranchTransaction;
import io.shardingsphere.transaction.saga.SagaBranchTransactionGroup;
import org.apache.shardingsphere.core.metadata.table.ShardingTableMetaData;
import org.apache.shardingsphere.core.parsing.parser.context.table.Tables;
import org.apache.shardingsphere.core.parsing.parser.sql.dml.DMLStatement;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SQLRevertEngineTest {
    
    private final SQLRevertEngine revertEngine = new SQLRevertEngine(new HashMap<String, Connection>());
    
    @Test
    public void assertRevert() throws SQLException {
        // TODO rewrite after SnapShotEngine complete
        SagaBranchTransaction sagaBranchTransaction = new SagaBranchTransaction("", "", Collections.<List<Object>>emptyList());
        DMLStatement dmlStatement = mock(DMLStatement.class);
        when(dmlStatement.getTables()).thenReturn(mock(Tables.class));
        SagaBranchTransactionGroup sagaBranchTransactionGroup = new SagaBranchTransactionGroup("", dmlStatement, mock(ShardingTableMetaData.class));
        SQLRevertResult result = revertEngine.revert(sagaBranchTransaction, sagaBranchTransactionGroup);
        assertThat(result.getSql(), is(""));
        assertThat(result.getParameterSets().size(), is(0));
    }
}
