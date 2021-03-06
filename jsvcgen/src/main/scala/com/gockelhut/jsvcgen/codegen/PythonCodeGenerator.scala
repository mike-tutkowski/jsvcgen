/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
**/
package com.gockelhut.jsvcgen.codegen

import scala.collection.immutable.Map
import scala.reflect.ClassTag
import com.gockelhut.jsvcgen.model._

class PythonCodeGenerator(options: CliConfig)
    extends BaseCodeGenerator(options, nickname=Some("python2")) {
  def formatTypeName(src: String) = Util.camelCase(src, true)
  
  def groupItemsToFiles(service: ServiceDefinition): Map[String, Any] = {
    Map(Util.camelCase(service.serviceName + ".py", true) -> service)
  }
  
  override protected def getDefaultMap[T](service: ServiceDefinition, value: T)(implicit tag: ClassTag[T]): Map[String, Any] =
    super.getDefaultMap(service, value) ++ Map("format" -> new PythonCodeFormatter(options, service))
}
