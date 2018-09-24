import { Link } from './link';
import { View } from './view';

export class ResponseData {
// tslint:disable-next-line:indent
	body: any;
	links: Link[];
	view: any;
	errorType: string;
	errorMessage: string;
	codes: string;
	errorMessagesForIds: string;
	errorBeans: ErrorBeans[];
	validationCodes: string;
	
}

export class ErrorBeans {
	errorType: string;
	errorMessage: string;
	validationCodes: string;
	errorMessagesForIds: string;
	codes: string;

}
