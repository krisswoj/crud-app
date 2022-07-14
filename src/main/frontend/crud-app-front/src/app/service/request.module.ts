import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CrudRoutingModule} from '../component/crud-routing.module';
import {IndexComponent} from '../component/index/index.component';
import {CreateComponent} from '../component/create/create.component';
import {EditComponent} from '../component/edit/edit.component';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormTemplateComponent} from "../component/form-template/form-template.component";

@NgModule({
  declarations: [IndexComponent, CreateComponent, EditComponent, FormTemplateComponent],
  imports: [
    CommonModule,
    CrudRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class RequestModule {
}
