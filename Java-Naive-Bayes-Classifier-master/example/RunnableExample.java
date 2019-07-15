import java.util.*;

import de.minorproject.machinelearning.classifier.bayes.BayesClassifier;
import de.minorproject.machinelearning.classifier.Classifier;

public class RunnableExample {
	

    public static void main(String[] args) {
    	
          /*
         * Create a new classifier instance. The context features are
         * Strings and the context will be classified with a String according
         * to the featureset of the context.
         */
        final Classifier<String, String> bayes =
                new BayesClassifier<String, String>();

        /*
         * The classifier can learn from classifications that are handed over
         * to the learn methods. Imagin a tokenized text as follows. The tokens
         * are the text's features. The category of the log will either be
         * harmless or harmful.
         */
        
        final String[] harmlesslog = "initialize procedure startupevent signaled start enqueuecommandobject dequeuecommandobject internal_initializeLogger final_OnConnect finished successfullyy created CreateLocalImageSession GetProviderCollection encountered internal_getProvider getting setting".split("\\s");
        bayes.learn("harmless", Arrays.asList(harmlesslog));

        final String[] harmfullog = "unable failed stop could not critical crash shutdown boot unmarshallImageHandleFromDirectory WIMGetMountedImageHandle incorrect decline poor may not point to a valid windows folder".split("\\s");
        bayes.learn("harmful", Arrays.asList(harmfullog));

    
        //ISM OS Provider: PID=5032 TID=11880 Unable to set the DLL search path to the servicing stack folder. C:\Windows may not point to a valid Windows folder. - CDISMOSServiceManager::Final_OnConnect
        //DISM Provider Store: PID=3284 TID=3312 Failed to Load the provider: C:WindowsTEMPACCE27E7-9BDB-4C24-8323-A734740E1E79PEProvider.dll. - CDISMProviderStore::Internal_GetProvider(hr:0x8007007e)
        //Found and Initialized the DISM Logger. - CDISMProviderStore::Internal_InitializeLogger
        //Successfully dequeued command object - CCommandThread::DequeueCommandObject

        
        final String[] unknownlog1 = "Lookup in table by path failed for: DummyPath-2BA51B78-C7F7-4910-B99D-BB7345357CDC - CTransactionalImageTable::LookupImagePath".split("\\s");
        final String[] unknownlog2 = "Successfully dequeued command object - CCommandThread::DequeueCommandObject".split("\\s");
        System.out.println( // will output "harmless"
                bayes.classify(Arrays.asList(unknownlog1)).getCategory());
        System.out.println( // will output "harmful"
                bayes.classify(Arrays.asList(unknownlog2)).getCategory());

        /*
         * The BayesClassifier extends the abstract Classifier and provides
         * detailed classification results that can be retrieved by calling
         * the classifyDetailed Method.
         *
         * The classification with the highest probability is the resulting
         * classification. The returned List will look like this.
         * [
         *   Classification [
         *     category=harmful,
         *     probability=0.0078125,
         *     featureset=[today, is, a, sunny, day]
         *   ],
         *   Classification [
         *     category=harmless,
         *     probability=0.0234375,
         *     featureset=[today, is, a, sunny, day]
         *   ]
         * ]
         */
        ((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownlog1));

        /*
         * Please note, that this particular classifier implementation will
         * "forget" learned classifications after a few learning sessions. The
         * number of learning sessions it will record can be set as follows:
         */
        bayes.setMemoryCapacity(500); // remember the last 500 learned classifications
    }

}
