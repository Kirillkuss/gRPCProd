// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: employee-service.proto

package com.rpc.itrail;

public final class EmployeeServiceOuterClass {
  private EmployeeServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EmployeeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EmployeeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EmployeeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EmployeeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026employee-service.proto\".\n\017EmployeeRequ" +
      "est\022\r\n\005empId\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\"0\n\020Empl" +
      "oyeeResponse\022\016\n\006emp_id\030\001 \001(\t\022\014\n\004name\030\002 \001" +
      "(\t2G\n\017EmployeeService\0224\n\013getEmployee\022\020.E" +
      "mployeeRequest\032\021.EmployeeResponse\"\000B\022\n\016c" +
      "om.rpc.itrailP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_EmployeeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EmployeeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EmployeeRequest_descriptor,
        new java.lang.String[] { "EmpId", "Name", });
    internal_static_EmployeeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_EmployeeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EmployeeResponse_descriptor,
        new java.lang.String[] { "EmpId", "Name", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
