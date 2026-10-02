#include <jni.h>
#include <dlfcn.h>
#include <cstdint>
#include <cstring>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <vector>
#include <unordered_map>
#include <mutex>

namespace {
using Il2CppDomain=void; using Il2CppAssembly=void; using Il2CppImage=void; using Il2CppClass=void;
using MethodInfo=void; using Il2CppObject=void; using Il2CppString=void; using Il2CppType=void;
using domain_get_t=Il2CppDomain*(*)();
using domain_get_assemblies_t=const Il2CppAssembly**(*)(Il2CppDomain*,size_t*);
using assembly_get_image_t=const Il2CppImage*(*)(const Il2CppAssembly*);
using image_get_name_t=const char*(*)(const Il2CppImage*);
using class_from_name_t=Il2CppClass*(*)(const Il2CppImage*,const char*,const char*);
using class_get_method_from_name_t=const MethodInfo*(*)(Il2CppClass*,const char*,int);
using class_get_methods_t=const MethodInfo*(*)(Il2CppClass*,void**);
using method_get_name_t=const char*(*)(const MethodInfo*);
using method_get_param_count_t=uint32_t(*)(const MethodInfo*);
using method_get_param_t=const Il2CppType*(*)(const MethodInfo*,uint32_t);
using type_get_name_t=char*(*)(const Il2CppType*);
using method_get_return_type_t=const Il2CppType*(*)(const MethodInfo*);
using runtime_invoke_t=Il2CppObject*(*)(const MethodInfo*,Il2CppObject*,void**,Il2CppObject**);
using object_new_t=Il2CppObject*(*)(Il2CppClass*);
using object_get_class_t=Il2CppClass*(*)(Il2CppObject*);
using object_unbox_t=void*(*)(Il2CppObject*);
using value_box_t=Il2CppObject*(*)(Il2CppClass*,void*);
using string_new_t=Il2CppString*(*)(const char*);
using class_get_name_t=const char*(*)(Il2CppClass*);
using class_get_namespace_t=const char*(*)(Il2CppClass*);
using image_get_class_count_t=size_t(*)(const Il2CppImage*);
using image_get_class_t=Il2CppClass*(*)(const Il2CppImage*,size_t);
using gchandle_new_t=uint32_t(*)(Il2CppObject*,bool);
using gchandle_get_target_t=Il2CppObject*(*)(uint32_t);
using gchandle_free_t=void(*)(uint32_t);

struct Api {
 void* h=nullptr;
 domain_get_t domain_get=nullptr; domain_get_assemblies_t domain_get_assemblies=nullptr;
 assembly_get_image_t assembly_get_image=nullptr; image_get_name_t image_get_name=nullptr;
 class_from_name_t class_from_name=nullptr; class_get_method_from_name_t class_get_method_from_name=nullptr;
 class_get_methods_t class_get_methods=nullptr; method_get_name_t method_get_name=nullptr;
 method_get_param_count_t method_get_param_count=nullptr; method_get_param_t method_get_param=nullptr;
 type_get_name_t type_get_name=nullptr; method_get_return_type_t method_get_return_type=nullptr;
 runtime_invoke_t runtime_invoke=nullptr; object_new_t object_new=nullptr;
 object_get_class_t object_get_class=nullptr; object_unbox_t object_unbox=nullptr;
 value_box_t value_box=nullptr; string_new_t string_new=nullptr;
 class_get_name_t class_get_name=nullptr; class_get_namespace_t class_get_namespace=nullptr;
 image_get_class_count_t image_get_class_count=nullptr; image_get_class_t image_get_class=nullptr;
 gchandle_new_t gchandle_new=nullptr; gchandle_get_target_t gchandle_get_target=nullptr;
 gchandle_free_t gchandle_free=nullptr;

 bool load(){
  if(domain_get) return true;
  h=dlopen("libil2cpp.so",RTLD_NOW|RTLD_NOLOAD);
  if(!h) h=dlopen("libil2cpp.so",RTLD_NOW);
  if(!h) return false;
#define S(v) v=(decltype(v))dlsym(h,"il2cpp_" #v)
  S(domain_get); S(domain_get_assemblies); S(assembly_get_image); S(image_get_name);
  S(class_from_name); S(class_get_method_from_name); S(class_get_methods);
  S(method_get_name); S(method_get_param_count); S(method_get_param); S(type_get_name);
  S(method_get_return_type); S(runtime_invoke); S(object_new); S(object_get_class);
  S(object_unbox); S(value_box); S(string_new); S(class_get_name); S(class_get_namespace);
  S(gchandle_new); S(gchandle_get_target); S(gchandle_free);
  S(image_get_class_count); S(image_get_class);
#undef S
  return domain_get&&domain_get_assemblies&&assembly_get_image&&class_from_name&&
         class_get_method_from_name&&runtime_invoke;
 }
 Il2CppImage* image(){
  size_t n=0; auto a=domain_get_assemblies(domain_get(),&n); if(!a)return nullptr;
  for(size_t i=0;i<n;i++){
   auto im=(Il2CppImage*)assembly_get_image(a[i]); if(!im)continue;
   auto nm=image_get_name?image_get_name(im):nullptr;
   if(nm&&(!strcmp(nm,"Assembly-CSharp")||!strcmp(nm,"Assembly-CSharp.dll")))return im;
  }
  return nullptr;
 }
 Il2CppClass* cls(const char* ns,const char* name){
  auto im=image(); return im?class_from_name(im,ns,name):nullptr;
 }
};
static Api g;
static std::mutex mu;
static std::unordered_map<long long,uint32_t> handles;
static long long nextHandle=1;

static std::string js(JNIEnv*e,jstring s){
 if(!s)return{};
 const char*p=e->GetStringUTFChars(s,nullptr); std::string r=p?p:"";
 e->ReleaseStringUTFChars(s,p); return r;
}
static jstring ret(JNIEnv*e,const std::string&s){return e->NewStringUTF(s.c_str());}

static long long put(Il2CppObject*o){
 if(!o)return 0;
 std::lock_guard<std::mutex>l(mu);
 if(g.gchandle_new){
   uint32_t gh=g.gchandle_new(o,false);
   if(!gh)return 0;
   long long id=nextHandle++;
   handles[id]=gh;
   return id;
 }
 return 0;
}
static Il2CppObject* get(long long id){
 std::lock_guard<std::mutex>l(mu);
 auto i=handles.find(id); if(i==handles.end())return nullptr;
 return g.gchandle_get_target?g.gchandle_get_target(i->second):nullptr;
}
static bool release(long long id){
 std::lock_guard<std::mutex>l(mu);
 auto i=handles.find(id); if(i==handles.end())return false;
 if(g.gchandle_free)g.gchandle_free(i->second);
 handles.erase(i); return true;
}
static std::string esc(const char*s){
 std::string o;
 if(!s)return o;
 for(;*s;s++){
  unsigned char c=(unsigned char)*s;
  if(c=='\\'||c=='"')o.push_back('\\'),o.push_back(c);
  else if(c=='\n')o+="\\n";
  else if(c=='\r')o+="\\r";
  else if(c=='\t')o+="\\t";
  else if(c<32){char b[8];snprintf(b,sizeof(b),"\\u%04x",c);o+=b;}
  else o.push_back((char)c);
 }
 return o;
}
static std::string obj(Il2CppObject*o){
 if(!o)return"null";
 if(!g.object_get_class||!g.class_get_name)return"OBJECT";
 auto c=g.object_get_class(o); const char*n=c?g.class_get_name(c):nullptr;
 if(!n)return"OBJECT";
 if(!strcmp(n,"String")){
   struct S{void*klass;void*monitor;int32_t len;uint16_t ch[1];};
   auto s=(S*)o; std::string out;
   for(int i=0;i<s->len;i++){uint16_t u=s->ch[i]; if(u<128)out.push_back((char)u); else out.push_back('?');}
   return out;
 }
 if(g.object_unbox){
   auto p=g.object_unbox(o);
   if(p){
    if(!strcmp(n,"Int32"))return std::to_string(*(int32_t*)p);
    if(!strcmp(n,"Boolean"))return *(uint8_t*)p?"true":"false";
    if(!strcmp(n,"Single")){char b[64];snprintf(b,sizeof(b),"%.9g",*(float*)p);return b;}
    if(!strcmp(n,"Double")){char b[64];snprintf(b,sizeof(b),"%.17g",*(double*)p);return b;}
   }
 }
 long long h=put(o);
 if(!h)return std::string("OBJECT:")+n;
 return std::string("HANDLE:")+std::to_string(h)+":"+n;
}
static Il2CppObject* box(const std::string&t,const std::string&v){
 if(t=="string")return g.string_new?(Il2CppObject*)g.string_new(v.c_str()):nullptr;
 if(t=="handle")return get(atoll(v.c_str()));
 const char*ns="System"; Il2CppClass*c=nullptr; size_t z=0;
 if(t=="int")c=g.cls(ns,"Int32"),z=4;
 else if(t=="float")c=g.cls(ns,"Single"),z=4;
 else if(t=="double")c=g.cls(ns,"Double"),z=8;
 else if(t=="bool")c=g.cls(ns,"Boolean"),z=1;
 if(!c||!g.object_new||!g.object_unbox)return nullptr;
 auto o=g.object_new(c); auto p=g.object_unbox(o); if(!p)return nullptr;
 if(t=="int")*(int32_t*)p=(int32_t)strtol(v.c_str(),nullptr,10);
 else if(t=="float")*(float*)p=strtof(v.c_str(),nullptr);
 else if(t=="double")*(double*)p=strtod(v.c_str(),nullptr);
 else if(t=="bool")*(uint8_t*)p=(!strcmp(v.c_str(),"true")||atoi(v.c_str())!=0);
 return o;
}
static std::string methodJson(const MethodInfo*m){
 const char*mn=g.method_get_name?g.method_get_name(m):nullptr;
 uint32_t pc=g.method_get_param_count?g.method_get_param_count(m):0;
 const char*rn=nullptr;
 if(g.type_get_name&&g.method_get_return_type){auto t=g.method_get_return_type(m); if(t)rn=g.type_get_name(t);}
 std::string out="{\"name\":\""+esc(mn)+"\",\"params\":[";
 for(uint32_t i=0;i<pc;i++){
  if(i)out+=",";
  const char*pn=nullptr;
  if(g.type_get_name&&g.method_get_param){auto t=g.method_get_param(m,i);if(t)pn=g.type_get_name(t);}
  out+="\""+esc(pn)+"\"";
 }
 out+="],\"count\":"+std::to_string(pc)+",\"return\":\""+esc(rn)+"\"}";
 return out;
}
static std::string describeClass(const char*ns,const char*cn){
 if(!g.load())return "{\"error\":\"IL2CPP unavailable\"}";
 auto k=g.cls(ns,cn); if(!k)return "{\"error\":\"class not found\"}";
 std::string out="{\"namespace\":\""+esc(ns)+"\",\"name\":\""+esc(cn)+"\",\"methods\":[";
 bool first=true; void*it=nullptr;
 if(g.class_get_methods){
  while(const MethodInfo*m=g.class_get_methods(k,&it)){
   if(!first)out+=",";
   first=false; out+=methodJson(m);
  }
 }
 out+="]}"; return out;
}

static std::string listClasses(){
 if(!g.load()) return "{\"error\":\"IL2CPP unavailable\"}";
 auto im=g.image();
 if(!im||!g.image_get_class_count||!g.image_get_class) return "{\"error\":\"class enumeration unavailable\"}";
 size_t n=g.image_get_class_count(im);
 std::string out="{\"assembly\":\"Assembly-CSharp\",\"count\":"+std::to_string(n)+",\"classes\":[";
 bool first=true;
 for(size_t i=0;i<n;i++){
   auto k=g.image_get_class(im,i); if(!k) continue;
   const char* ns=g.class_get_namespace?g.class_get_namespace(k):"";
   const char* cn=g.class_get_name?g.class_get_name(k):"";
   if(!first) out+=",";
   first=false;
   out+="{\"namespace\":\""+esc(ns)+"\",\"name\":\""+esc(cn)+"\"}";
 }
 out+="]}";
 return out;
}
static std::string findMethodInfo(const MethodInfo*m){
 if(!m) return "{\"error\":\"method not found\"}";
 return methodJson(m);
}
static jstring invoke(JNIEnv*e,Il2CppObject*receiver,const std::string&mn,jobjectArray args){
 auto k=receiver?g.object_get_class(receiver):nullptr; if(!k)return ret(e,"ERROR:bad receiver");
 jsize ac=args?e->GetArrayLength(args):0;
 auto m=g.class_get_method_from_name(k,mn.c_str(),ac); if(!m)return ret(e,"ERROR:method not found");
 std::vector<Il2CppObject*>boxed(ac); std::vector<void*>argv(ac);
 for(int i=0;i<ac;i++){
  auto jo=(jstring)e->GetObjectArrayElement(args,i); auto s=js(e,jo); e->DeleteLocalRef(jo);
  auto p=s.find(':'); if(p==std::string::npos)return ret(e,"ERROR:arg must be type:value");
  auto tn=s.substr(0,p); boxed[i]=box(tn,s.substr(p+1)); if(!boxed[i])return ret(e,"ERROR:bad argument");
  argv[i]=(tn=="int"||tn=="float"||tn=="double"||tn=="bool")?g.object_unbox(boxed[i]):(void*)&boxed[i];
 }
 Il2CppObject*ex=nullptr; auto r=g.runtime_invoke(m,receiver,ac?argv.data():nullptr,&ex);
 if(ex)return ret(e,"ERROR:runtime exception");
 return ret(e,obj(r));
}
}
extern "C" JNIEXPORT jboolean JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeAvailable(JNIEnv*,jobject){return g.load()?JNI_TRUE:JNI_FALSE;}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeFindClass(JNIEnv*e,jobject,jstring ns,jstring name){
 if(!g.load())return ret(e,"ERROR:IL2CPP unavailable");
 auto n=js(e,ns),c=js(e,name); auto k=g.cls(n.c_str(),c.c_str());
 return ret(e,k?n+"."+c:"NOT_FOUND");
}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeDescribeClass(JNIEnv*e,jobject,jstring ns,jstring name){
 auto n=js(e,ns),c=js(e,name); return ret(e,describeClass(n.c_str(),c.c_str()));
}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeInvokeStatic(JNIEnv*e,jobject,jstring ns,jstring cl,jstring method,jobjectArray args){
 if(!g.load())return ret(e,"ERROR:IL2CPP unavailable");
 auto n=js(e,ns),c=js(e,cl),mn=js(e,method); auto k=g.cls(n.c_str(),c.c_str()); if(!k)return ret(e,"ERROR:class not found");
 jsize ac=args?e->GetArrayLength(args):0; auto m=g.class_get_method_from_name(k,mn.c_str(),ac); if(!m)return ret(e,"ERROR:method not found");
 std::vector<Il2CppObject*>boxed(ac); std::vector<void*>argv(ac);
 for(int i=0;i<ac;i++){
  auto jo=(jstring)e->GetObjectArrayElement(args,i);auto s=js(e,jo);e->DeleteLocalRef(jo);
  auto p=s.find(':');if(p==std::string::npos)return ret(e,"ERROR:arg must be type:value");
  auto tn=s.substr(0,p);boxed[i]=box(tn,s.substr(p+1));if(!boxed[i])return ret(e,"ERROR:bad argument");
  argv[i]=(tn=="int"||tn=="float"||tn=="double"||tn=="bool")?g.object_unbox(boxed[i]):(void*)&boxed[i];
 }
 Il2CppObject*ex=nullptr;auto r=g.runtime_invoke(m,nullptr,ac?argv.data():nullptr,&ex);
 if(ex)return ret(e,"ERROR:runtime exception");
 return ret(e,obj(r));
}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeInvoke(JNIEnv*e,jobject,jlong handle,jstring method,jobjectArray args){
 if(!g.load())return ret(e,"ERROR:IL2CPP unavailable");
 auto o=get((long long)handle);if(!o)return ret(e,"ERROR:bad handle");
 return invoke(e,o,js(e,method),args);
}

extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeListClasses(JNIEnv*e,jobject){
 return ret(e,listClasses());
}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeDescribeHandle(JNIEnv*e,jobject,jlong handle){
 if(!g.load()) return ret(e,"{\"error\":\"IL2CPP unavailable\"}");
 auto o=get((long long)handle); if(!o) return ret(e,"{\"error\":\"bad handle\"}");
 auto k=g.object_get_class?g.object_get_class(o):nullptr; if(!k) return ret(e,"{\"error\":\"bad object class\"}");
 const char* ns=g.class_get_namespace?g.class_get_namespace(k):"";
 const char* cn=g.class_get_name?g.class_get_name(k):"";
 return ret(e,describeClass(ns,cn));
}
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeInvoke0(JNIEnv*e,jobject,jlong handle,jstring method){
 if(!g.load()) return ret(e,"ERROR:IL2CPP unavailable");
 auto o=get((long long)handle); if(!o) return ret(e,"ERROR:bad handle");
 return invoke(e,o,js(e,method),nullptr);
}
extern "C" JNIEXPORT jboolean JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoScriptBridge_nativeRelease(JNIEnv*,jobject,jlong handle){
 return release((long long)handle)?JNI_TRUE:JNI_FALSE;
}
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM*,void*){return JNI_VERSION_1_6;}
