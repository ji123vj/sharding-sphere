/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.filler;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import io.shardingsphere.core.parsing.antlr.filler.engnie.ColumnDefinitionFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.ConstraintDefinitionFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.DropColumnFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.DropPrimaryKeyFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.FromWhereFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.GroupByFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.IndexFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.LimitFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.OrderByFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.SelectClauseFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.ShowParamFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.TableJoinFiller;
import io.shardingsphere.core.parsing.antlr.filler.engnie.TableFiller;
import io.shardingsphere.core.parsing.antlr.sql.segment.FromWhereSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.IndexSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.LimitSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.SQLSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.SelectClauseSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.ShowParamSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.column.ColumnDefinitionSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.column.DropColumnSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.constraint.ConstraintDefinitionSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.constraint.DropPrimaryKeySegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.order.GroupBySegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.order.OrderBySegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.table.TableJoinSegment;
import io.shardingsphere.core.parsing.antlr.sql.segment.table.TableSegment;

/**
 * SQL Segment filler registry.
 *
 * @author duhongjun
 */
public final class SQLSegmentFillerRegistry {
    
    private static final Map<Class<?>, SQLSegmentFiller> FILLERS = new HashMap<>();
    
    static {
        FILLERS.put(DropColumnSegment.class, new DropColumnFiller());
        FILLERS.put(ConstraintDefinitionSegment.class, new ConstraintDefinitionFiller());
        FILLERS.put(DropPrimaryKeySegment.class, new DropPrimaryKeyFiller());
        FILLERS.put(TableSegment.class, new TableFiller());
        FILLERS.put(ColumnDefinitionSegment.class, new ColumnDefinitionFiller());
        FILLERS.put(IndexSegment.class, new IndexFiller());
        FILLERS.put(SelectClauseSegment.class, new SelectClauseFiller());
        FILLERS.put(FromWhereSegment.class, new FromWhereFiller());
        FILLERS.put(TableJoinSegment.class, new TableJoinFiller());
        FILLERS.put(GroupBySegment.class, new GroupByFiller());
        FILLERS.put(OrderBySegment.class, new OrderByFiller());
        FILLERS.put(LimitSegment.class, new LimitFiller());
        FILLERS.put(ShowParamSegment.class, new ShowParamFiller());
    }
    
    /**
     * Find SQL segment filler.
     *
     * @param sqlSegment sql segment
     * @return instance of SQL segment filler
     */
    public static Optional<SQLSegmentFiller> findFiller(final SQLSegment sqlSegment) {
        return Optional.fromNullable(FILLERS.get(sqlSegment.getClass()));
    }
}
