import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { ElectionsModule } from './elec.module';

platformBrowserDynamic().bootstrapModule(ElectionsModule).catch(err => console.log(err));