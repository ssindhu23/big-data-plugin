/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package org.pentaho.big.data.kettle.plugins.mapreduce;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pentaho.hadoop.shim.api.cluster.NamedCluster;
import org.pentaho.hadoop.shim.api.cluster.NamedClusterService;
import org.pentaho.hadoop.shim.api.cluster.NamedClusterServiceLocator;
import org.pentaho.big.data.kettle.plugins.mapreduce.entry.pmr.JobEntryHadoopTransJobExecutor;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.metastore.api.IMetaStore;
import org.pentaho.runtime.test.RuntimeTester;
import org.pentaho.runtime.test.action.RuntimeTestActionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JobEntryHadoopTransJobExecutorTest {
  private static NamedClusterService namedClusterService;
  private static RuntimeTestActionService runtimeTestActionService;
  private static RuntimeTester runtimeTester;
  private static NamedClusterServiceLocator namedClusterServiceLocator;
  private static JobEntryHadoopTransJobExecutor exec;
  private static Repository rep;
  private static ObjectId oid;
  private static IMetaStore metaStore;

  @BeforeClass
  public static final void setup() throws Throwable {
    namedClusterService = mock( NamedClusterService.class );
    when( namedClusterService.getClusterTemplate() ).thenReturn( mock( NamedCluster.class ) );
    runtimeTestActionService = mock( RuntimeTestActionService.class );
    runtimeTester = mock(
      RuntimeTester.class );
    namedClusterServiceLocator = mock( NamedClusterServiceLocator.class );
    rep = mock( Repository.class );
    metaStore = mock( IMetaStore.class );
    oid = mock( ObjectId.class );
    exec = new JobEntryHadoopTransJobExecutor( namedClusterService, runtimeTestActionService, runtimeTester,
      namedClusterServiceLocator );
  }

  @Test
  public void loadRep_num_map_tasks_null() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_map_tasks" ) ).thenReturn( null );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( null, exec.getNumMapTasks() );
  }

  @Test
  public void loadRep_num_map_tasks_empty() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_map_tasks" ) ).thenReturn( "" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "", exec.getNumMapTasks() );
  }

  @Test
  public void loadRep_num_map_tasks_variable() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_map_tasks" ) ).thenReturn( "${test}" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "${test}", exec.getNumMapTasks() );
  }

  @Test
  public void loadRep_num_map_tasks_number() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_map_tasks" ) ).thenReturn( "5" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "5", exec.getNumMapTasks() );
  }

  @Test
  public void loadRep_num_reduce_tasks_null() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_reduce_tasks" ) ).thenReturn( null );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( null, exec.getNumReduceTasks() );
  }

  @Test
  public void loadRep_num_reduce_tasks_empty() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_reduce_tasks" ) ).thenReturn( "" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "", exec.getNumReduceTasks() );
  }

  @Test
  public void loadRep_num_reduce_tasks_variable() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_reduce_tasks" ) ).thenReturn( "${test}" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "${test}", exec.getNumReduceTasks() );
  }

  @Test
  public void loadRep_num_reduce_tasks_number() throws Throwable {
    when( rep.getJobEntryAttributeString( oid, "num_reduce_tasks" ) ).thenReturn( "5" );
    exec.loadRep( rep, metaStore, oid, null, null );
    assertEquals( "5", exec.getNumReduceTasks() );
  }
}
